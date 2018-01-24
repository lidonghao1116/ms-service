package com.jiacer.modules.clientInterface.model;


import com.jiacer.modules.clientInterface.common.conts.Constants;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 红包类
 *
 * 字段名	字段	必填	示例值	类型	说明
 随机字符串	nonce_str	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	String(32)	随机字符串，不长于32位
 签名	    sign	是	C380BEC2BFD727A4B6845133519F3AD6	String(32)	详见签名生成算法
 商户订单号	mch_billno	是	10000098201411111234567890	String(28)  商户订单号（每个订单号必须唯一。取值范围：0~9，a~z，A~Z）
                                                                    接口根据商户订单号支持重入，如出现超时可再调用。
 商户号	    mch_id	是	10000098	String(32)	微信支付分配的商户号
 公众账号appid	wxappid	是	wx8888888888888888	String(32)	微信分配的公众账号ID（企业号corpid即为此appId）。接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的），不能为APP的appid（在open.weixin.qq.com申请的）。
 商户名称	        send_name	是	天虹百货	String(32)	红包发送者名称
 用户             openid	re_openid	是	oxTWIuGaIt6gTKsQRLau2M0yL16E	String(32)  接受红包的用户 用户在wxappid下的openid
 付款金额	            total_amount	是	1000	int	付款金额，单位分
 红包发放总人数	    total_num	是	1	int         红包发放总人数     total_num=1
 红包祝福语	        wishing	是	感谢您参加猜灯谜活动，祝您元宵节快乐！	String(128)	红包祝福语
 Ip地址	            client_ip	是	192.168.0.1	String(15)	调用接口的机器Ip地址
 活动名称	            act_name	是	猜灯谜抢红包活动	String(32)	活动名称
 备注	            remark	是	猜越多得越多，快来抢！	String(256)	备注信息
 场景id	            scene_id	否	PRODUCT_8	String(32)
                                                 发放红包使用场景，红包金额大于200时必传
                                                 PRODUCT_1:商品促销
                                                 PRODUCT_2:抽奖
                                                 PRODUCT_3:虚拟物品兑奖
                                                 PRODUCT_4:企业内部福利
                                                 PRODUCT_5:渠道分润
                                                 PRODUCT_6:保险回馈
                                                 PRODUCT_7:彩票派奖
                                                 PRODUCT_8:税务刮奖
 活动信息       	risk_info	否	posttime%3d123123412%26clientversion%3d234134%26mobile%3d122344545%26deviceid%3dIOS	String(128)
                                 posttime:用户操作的时间戳
                                 mobile:业务系统账号的手机号，国家代码-手机号。不需要+号
                                 deviceid :mac 地址或者设备唯一标识
                                 clientversion :用户操作的客户端版本
                                 把值为非空的信息用key=value进行拼接，再进行urlencode
                                 urlencode(posttime=xx& mobile =xx&deviceid=xx)
 资金授权商户号	consume_mch_id	否	1222000096	String(32)  资金授权商户号  服务商替特约商户发放时使用

 * Created by gaoyan on 30/06/2017.
 * link: <a href="https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3">api</a>
 */
public class RedpackInfo implements Serializable{

    private Integer id;

    private String nonceStr;    //随机字符串
    private String sign;        //签名
    private String mchBillno;   //商户订单号
    private String mchId;       //商户号

    private String wxappid;     //公众账号appid
    private String send_name;   //商户名称
    private String reOpenid;    //用户openid
    private String totalAmount; //付款金额
    private String totalNum;   //发放红包总人数
    private String  wishing;    //祝福语
    private String  clientIp;   //调用接口的机器Ip地址
    private String  actName;    //活动名称
    private String  remark;     //备注
    private String  sceneId;    //使用场景，红包大于200必传

    private String  riskInfo;   //活动信息
    private String  consumeMchId; //资金授权商户号

    public RedpackInfo(){}

    public RedpackInfo(String mchBillno, String reOpenid, String amount) {
        this.nonceStr = RandomStringUtils.random(32,true,true);
        this.mchBillno = mchBillno;
        this.mchId = Constants.WECHAT_MERCHANT_ID;
        this.wxappid = Constants.APPID;
        this.send_name = Constants.WECHAT_MERCHANT_NAME;
        this.reOpenid = reOpenid;
        this.totalAmount = amount;
        this.totalNum = "1";
        this.wishing = "邀请更多，惊喜更多！";
        try {
            this.clientIp =  InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            this.clientIp = "127.0.0.1";
        }
        this.actName = "邀请好友开通在线课程";
        this.remark = "成功邀请好友购买在线课程后发放红包.";
    }

    public RedpackInfo(RedpackInfo red) {
        this.nonceStr = red.getNonceStr();
        this.mchBillno = red.getMchBillno();
        this.mchId = red.getMchId();
        this.wxappid = red.getWxappid();
        this.send_name = red.getSend_name();
        this.reOpenid = red.getReOpenid();
        this.totalAmount = red.getTotalAmount();
        this.totalNum = red.getTotalNum();
        this.wishing = red.getWishing();
        this.clientIp =  red.getClientIp();
        this.actName = red.getActName();
        this.remark = red.getRemark();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getReOpenid() {
        return reOpenid;
    }

    public void setReOpenid(String reOpenid) {
        this.reOpenid = reOpenid;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getRiskInfo() {
        return riskInfo;
    }

    public void setRiskInfo(String riskInfo) {
        this.riskInfo = riskInfo;
    }

    public String getConsumeMchId() {
        return consumeMchId;
    }

    public void setConsumeMchId(String consumeMchId) {
        this.consumeMchId = consumeMchId;
    }
}
