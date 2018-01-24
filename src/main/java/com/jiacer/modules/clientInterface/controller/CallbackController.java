package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.clientInterface.common.SwiftpassConfig;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.clientInterface.common.payGateway.SignUtils;
import com.jiacer.modules.clientInterface.common.payGateway.XmlUtils;
import com.jiacer.modules.clientInterface.model.SwiftpassCallbackInfo;
import com.jiacer.modules.clientInterface.service.FinanceFlowService;
import com.jiacer.modules.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 支付回调
 * 版本号	version	是	String(8)	版本号，version默认值是2.0。
 * 字符集	    charset	是	String(8)	可选值 UTF-8 ，默认为 UTF-8。
 * 签名方式	    sign_type	是	String(8)	签名类型，取值：MD5默认：MD5
 * 返回状态码	status	是	String(16)	0表示成功非0表示失败此字段是通信标识，非交易标识，交易是否成功需要查看 result_code 来判断标识，交易是否成功需要查看 result_code 来判断
 * 返回信息   	message	否	String(128)	返回信息，如非空，为错误原因签名失败参数格式校验错误
 * 以下字段在  status 为 0的时候有返回
 * 业务结果    result_code	是	String(16)	0表示成功非0表示失败
 * 商户号	    mch_id	是	String(32)	商户号，由平台分配
 * 设备号	    device_info	否	String(32)	终端设备号
 * 随机字符串	nonce_str	是	String(32)	随机字符串，不长于 32 位
 * 错误代码	    err_code	否	String(32)	参考错误码
 * 错误代码描述	err_msg	否	String (128)	结果信息描述
 * 签名	    sign	是	String(32)	MD5签名结果，详见“安全规范”
 * 以下字段在status 和 result_code 都为 0的时候有返回
 * 用户标识	    openid	是	String(128)	用户在商户 appid 下的唯一标识
 * 交易类型	    trade_type	是	String(32)	pay.weixin.jspay
 * 是否关注公众账号	is_subscribe	是	String(1)	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
 * 支付结果   	pay_result	是	Int	支付结果：0—成功；其它—失败
 * 支付结果信息	pay_info	否	String(64)	支付结果信息，支付成功时为空
 * 平台订单号	transaction_id	是	String(32)	平台交易号
 * 第三方订单号	out_transaction_id	是	String(32)	第三方订单号
 * 子商户是否关注	sub_is_subscribe	否	String(1)	用户是否关注子公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
 * 子商户appid	    sub_appid	否	String	子商户appid
 * 用户openid	    sub_openid	否	String(128)	用户在商户 sub_appid 下的唯一标识
 * 商户订单号	    out_trade_no	是	String(32)	商户系统内部的定单号，32个字符内、可包含字母
 * 总金额	    total_fee	是	Int	总金额，以分为单位，不允许包含任何字、符号
 * 现金券金额	coupon_fee	否	Int	现金券支付金额<=订单总金额， 订单总金额-现金券金额为现金支付金额
 * 货币种类	    fee_type	否	String(8)	货币类型，符合 ISO 4217 标准的三位字母代码，默认人民币：CNY
 * 附加信息	    attach	否	String(127)	商家数据包，原样返回
 * 付款银行   	bank_type	是	String(16)	银行类型
 * 银行订单号	bank_billno	否	String(32)	银行订单号，若为微信支付则为空
 * 支付完成时间	time_end	是	String(14)	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8 beijing。该时间取自平台服务器
 */
@RestController
@RequestMapping("/api/callback")
public class CallbackController {

    private static final Logger log = LoggerFactory.getLogger("callbackLogger");

    @Autowired
    private FinanceFlowService finalceFlowService;

    /**
     * 支付回调接口
     *
     * @return
     */
    @Guest
    @RequestMapping(value = "/swiftpass", method = RequestMethod.POST)
    @ResponseBody
    public String callback(HttpServletRequest req) {
        log.info("===[swiftpass callback ]===");
        SwiftpassCallbackInfo sp = null;
        log.info("收到通知...");
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("req.setCharacterEncoding error."+e.getMessage());
        }
        String resString = XmlUtils.parseRequst(req);
          //String resString = "<xml><bank_type><![CDATA[CFT]]></bank_type><charset><![CDATA[UTF-8]]></charset><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[102561827285]]></mch_id><nonce_str><![CDATA[1499752815913]]></nonce_str><openid><![CDATA[oMJGHszixhv1JAFiuFMwNXrqkwzw]]></openid><out_trade_no><![CDATA[122017071100087]]></out_trade_no><out_transaction_id><![CDATA[4007792001201707110121088496]]></out_transaction_id><pay_result><![CDATA[0]]></pay_result><result_code><![CDATA[0]]></result_code><sign><![CDATA[1775E1E4E5611B99CBCEE49353E35AEC]]></sign><sign_type><![CDATA[MD5]]></sign_type><status><![CDATA[0]]></status><sub_appid><![CDATA[wxae59a35c51f7e95c]]></sub_appid><sub_is_subscribe><![CDATA[Y]]></sub_is_subscribe><sub_openid><![CDATA[oL8lSwf4B8Tqw3B2F1V6f32rQxbA]]></sub_openid><time_end><![CDATA[20170711140015]]></time_end><total_fee><![CDATA[1]]></total_fee><trade_type><![CDATA[pay.weixin.jspay]]></trade_type><transaction_id><![CDATA[102561827285201707118138231175]]></transaction_id><version><![CDATA[2.0]]></version></xml>";
          log.info("通知内容：" + resString);

        String respString = "error";
        if (resString == null || "".equals(resString)) {
            log.error("返回报文不正确");
            return "fail";
        }
        Map<String, String> map = null;
        try {
            map = XmlUtils.toMap(resString.getBytes(), "utf-8");
        } catch (Exception e) {
            log.error("XmlUtils.toMap error."+e.getMessage());
        }
        String res = JsonUtils.toJson(map);
        sp = JsonUtils.fromJson(res, SwiftpassCallbackInfo.class);
        if (!map.containsKey("sign")) {
            log.error("返回报文没有签名信息");
            return "fail";
        }
        if (!sp.getSignType().equalsIgnoreCase("fix") && !SignUtils.checkParam(map, SwiftpassConfig.key)) {
            log.error("验证签名不通过");
            return "fail";
        }
        String status = map.get("status");
        if (status == null || !"0".equals(status)) {
            log.error(String.format("响应码错误[%s]", status));
            return "fail";
        }
        String result_code = map.get("result_code");
        if (result_code == null || !"0".equals(result_code)) {
            log.error(String.format("返回代码[%s]", result_code));
            return "fail";
        }
        sp.setResultXml(resString);
        Object obj = finalceFlowService.processSwift(sp);
        return (String)obj;
    }
}
