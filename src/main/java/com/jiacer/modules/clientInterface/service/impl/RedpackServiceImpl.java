package com.jiacer.modules.clientInterface.service.impl;

import com.jiacer.modules.clientInterface.common.GenerateSerialNumber;
import com.jiacer.modules.clientInterface.common.conts.*;
import com.jiacer.modules.clientInterface.common.payGateway.MD5;
import com.jiacer.modules.clientInterface.common.payGateway.SignUtils;
import com.jiacer.modules.clientInterface.common.payGateway.XmlUtils;
import com.jiacer.modules.clientInterface.jsonResult.RedpackJsonData;
import com.jiacer.modules.clientInterface.model.FinanceFlowInfo;
import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.model.RedpackExtendInfo;
import com.jiacer.modules.clientInterface.model.RedpackInfo;
import com.jiacer.modules.clientInterface.service.RedpackService;
import com.jiacer.modules.common.page.vo.RedpackVo;
import com.jiacer.modules.common.utils.JsonUtils;
import com.jiacer.modules.mybatis.dao.FinanceFlowMapper;
import com.jiacer.modules.mybatis.dao.RedpackMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.model.UserBaseInfo;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.SSLContext;
import java.io.BufferedInputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * 红包业务
 * Created by gaoyan on 30/06/2017.
 */
public class RedpackServiceImpl implements RedpackService {

    private static final Logger log = LoggerFactory.getLogger(RedpackServiceImpl.class);


    @Autowired
    private UserBaseInfoMapper userDao;
    @Autowired
    private RedpackMapper redpackMapper;

    @Autowired
    private GenerateSerialNumber generater;

    @Autowired
    private FinanceFlowMapper financeFlowMapper;

    /**
     * 查询该邀请码是否有效
     *
     * @param inviterCode
     * @return
     */
    @Override
    public boolean isAvilabilityForInviterCode(String inviterCode) {
        if (StringUtils.isBlank(inviterCode)) {
            return false;
        }
        try {
            UserBaseInfoEntity user = userDao.getByInviteCode(inviterCode);
            return user != null;
        } catch (Exception e) {
            log.error("获取邀请码失败[{}]", inviterCode);
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 获取红包列表
     * @return
     */
    @Override
    public List<RedpackJsonData> getListByUserId(RedpackVo vo) {
        return redpackMapper.getListByUserId(vo);
    }

    @Override
    public Map<String, Object> total(Integer userId) {
        return redpackMapper.total(userId);
    }

    /**
     * 发送红包
     */
    @Override
    public void send(final OnlineOrderInfo orderInfo) {
        log.info("发送红包 orderNo = "+orderInfo.getOrderNo());
        final String orderNo = orderInfo.getOrderNo();
        final UserBaseInfo user = userDao.getByInviteCode(orderInfo.getInviterCode());
        final UserBaseInfo currentUser = userDao.getById(orderInfo.getUserId());
        //红包金额8%，最大9.9
        Long fee = Math.round(orderInfo.getAmount() * 0.08 > 9.9 ? 9.9*100 : orderInfo.getAmount() * 0.08 * 100);
        String amount = fee < 100 ? "100" : fee+"";

        if(user.getId().equals(currentUser.getId())){
            log.error("当前用户与发红包用户是同一用户， 不可发红包");
            return;
        }

        final RedpackInfo red = new RedpackInfo(generater.take(SequeConst.REDPACK), user.getOpenId(), amount);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                SortedMap<String, String> map = JsonUtils.fromJson(JsonUtils.toJson(red), TreeMap.class);
                Map<String, String> params = SignUtils.paraFilter(map);
                StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
                SignUtils.buildPayParams(buf, params, false);
                String preStr = buf.toString();
                String sign = MD5.sign(preStr, "&key=" + Constants.WECHAT_MERCHANT_KEY, "utf-8");
                map.put("sign", sign);

                String reqXml = XmlUtils.parseXML(map);

                //1. 记录发红包请求
                RedpackExtendInfo rex = new RedpackExtendInfo(red);
                rex.setOrderNo(orderNo);
                rex.setRequestTime(new Date());
                rex.setRequestInfo(reqXml);
                rex.setUserId(orderInfo.getUserId());
                rex.setUserName(currentUser.getWechatNick());
                rex.setInviterId(user.getId());
                rex.setInviterName(user.getWechatNick());
                rex.setInviterOpenid(user.getOpenId());
                rex.setInviterMobile(user.getMobile());
                rex.setPayStatus(RedpackConst.PayStatus.SENDING.getKey());
                rex.setStatus(RedpackConst.Status.WAIT_SETTLEMENT.getKey());
                rex.setIsUsable(RedpackConst.IsUsable.YES.getKey());
                redpackMapper.insert(rex);

                try {
                    log.info(String.format("===== redpack send , billno[%s]==={%s}" ,red.getMchBillno(), reqXml));
                    //2. 发送红包
                    String result = postWechatRedpack(reqXml);
                    log.info(String.format("===== redpack send , billno[%s]===%s" ,red.getMchBillno(), result));
                    //3. 更新请求结果
                    rex = new RedpackExtendInfo();
                    rex.setRedpackNo(red.getMchBillno());
                    rex.setResponseTime(new Date());
                    rex.setResponseInfo(result);
                    Map<String,String> resultMap = XmlUtils.toMap(result.getBytes(),"UTF-8");
                    if(resultMap != null){
                        rex.setFailCode(resultMap.get("err_code"));
                        rex.setFailReason(resultMap.get("err_code_des"));
                        rex.setIsNeedRetry(RedpackConst.ErrorCode.get(rex.getFailCode()).getIsNeedRetry());
                        rex.setIsReminded(RedpackConst.ErrorCode.get(rex.getFailCode()).getIsRemind());
                        rex.setRetryTimes(0);
                    }
                    if(RedpackConst.ErrorCode.SUCCESS.getCode().equalsIgnoreCase(resultMap.get("result_code"))){
                        rex.setFailCode(RedpackConst.ErrorCode.SUCCESS.getCode());
                        rex.setPayStatus(RedpackConst.PayStatus.SEND_FINISHED.getKey());
                        rex.setIsNeedRetry(RedpackConst.ErrorCode.SUCCESS.getIsNeedRetry());
                        rex.setIsReminded(RedpackConst.ErrorCode.SUCCESS.getIsRemind());
                        rex.setTotalAmount(resultMap.get("total_amount"));
                        rex.setUserId(user.getId());
                        rex.setRedpackWxNo(resultMap.get("send_listid"));
                        log.info(String.format("红包发送成功[%s]",rex.getRedpackNo()));
                    }else{
                        rex.setPayStatus(RedpackConst.PayStatus.SEND_FAILURED.getKey());
                        log.info(String.format("红包发送失败[%s]",rex.getRedpackNo()));
                    }
                } catch (Exception e) {
                    log.error(String.format("redpack_no[%s],红包处理失败：%s",rex.getRedpackNo(), e.getMessage()));
                }
                //更新红包发送状态
                log.info(String.format("修改红包状态[%s]",rex.getRedpackNo()));
                redpackMapper.update(rex);

                if(RedpackConst.ErrorCode.SUCCESS.getCode().equalsIgnoreCase(rex.getFailCode())){
                    //添加红包流水
                    FinanceFlowInfo finance = new FinanceFlowInfo();
                    finance.setAddTime(new Date());
                    finance.setUserId(rex.getUserId());
                    finance.setAmount(rex.getStandAmount());
                    finance.setDirection(FinanceConst.Direction.INCOME.getCode());
                    finance.setFlowType(FinanceConst.FlowType.REDPACK_GET.getCode());
                    finance.setTradeChannel(OrderConst.TradeChannel.WECHATPAY.getService());
                    finance.setOriginChannel(FinanceConst.OriginChannel.JIACER_ONLINE_SCHOOL_APP.getCode());
                    finance.setOutTradeNo(rex.getRedpackNo());
                    finance.setStatus("1"); //1.有效,0无效
                    finance.setPayStatus(OrderConst.PayStatus.PAID.getCode());
                    finance.setFinanceNo(generater.take(SequeConst.FINANCE_FLOW));
                    financeFlowMapper.insert(finance);
                    log.info(String.format("添加红包流水[%s]",rex.getRedpackNo()));
                }

                log.info(String.format("红包发送完成[%s]",rex.getRedpackNo()));
            }
        });
    }


    public String postWechatRedpack(String param) throws Exception {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        BufferedInputStream instream = (BufferedInputStream)this.getClass().getResourceAsStream("/apiclient_cert.p12");
        try {
            keyStore.load(instream, Constants.WECHAT_MERCHANT_ID.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, Constants.WECHAT_MERCHANT_ID.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();

        HttpPost post = new HttpPost(Constants.WECHAT_REDPACK_URL);
        CloseableHttpResponse response = null;
        try {
            HttpEntity body = new StringEntity(param, Charset.forName("UTF-8"));
            post.setEntity(body);
            response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(response.getEntity());
            }
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
            post.releaseConnection();
        }
    }

    /**
     * 红包重发机制
     */
    public void retrySendRedpack(){
        throw new NotImplementedException("redpack retry send is not implement. please contact to admin");
    }

}
