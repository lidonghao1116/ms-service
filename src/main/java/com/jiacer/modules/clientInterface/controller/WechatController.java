package com.jiacer.modules.clientInterface.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.common.SessionManager;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.wechat.core.TokenSingleton;
import net.sf.json.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.clientInterface.common.RequestMappingURL;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(RequestMappingURL.WECHAT_BASE_URL)
public class WechatController {

    private final static Logger log = LoggerFactory.getLogger(WechatController.class);

    private final static String UTF8 = "UTF8";

    /**
     * 接收微信平台的GET事件提交,主要用于微信接入服务器TOKEN验证
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param response
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Guest
    @RequestMapping(value = RequestMappingURL.PROCESS_URL, method = RequestMethod.GET)
    public void signature(@RequestParam(value = "signature", required = true) String signature,
                          @RequestParam(value = "timestamp", required = true) String timestamp,
                          @RequestParam(value = "nonce", required = true) String nonce,
                          @RequestParam(value = "echostr", required = true) String echostr, HttpServletResponse response)
            throws IOException {
        String[] values = {Constants.TOKEN, timestamp, nonce};
        Arrays.sort(values); // 字典序排序
        String value = values[0] + values[1] + values[2];
        String sign = DigestUtils.shaHex(value);
        PrintWriter writer = response.getWriter();
        if (signature.equals(sign)) {// 验证成功返回ehcostr
            writer.print(echostr);
        } else {
            writer.print("error");
        }
        writer.flush();
        writer.close();
    }

    @Guest
    @RequestMapping(value = "/share/sign", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getWebShareJsSign(String urlpath) {

        Map<String, Object> ret = new HashMap();
        String appId = Constants.APPID;
        String secret = Constants.APPSECRET;
        String requestUrl = urlpath;
        log.info(requestUrl + "*************************");
        String access_token = "";
        String jsapi_ticket = "";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000L);
        String nonceStr = UUID.randomUUID().toString();


        Map tokenMap = TokenSingleton.getInstance().getMap();
        if (tokenMap.get("jsapi_token") != null) {
            jsapi_ticket = (String) tokenMap.get("jsapi_token");
            log.info(jsapi_ticket + "===========jsapi_ticket");
        }
        String signature = "";

        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + requestUrl;

        log.info(sign + "-------------------");
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sign.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("appId", appId);
        ret.put("timestamp", timestamp);
        ret.put("nonceStr", nonceStr);
        ret.put("signature", signature);
        log.info("signature=============" + signature);
        return JsonResult.success(ret);
    }

    private static String byteToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", new Object[]{Byte.valueOf(b)});
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    @Guest
    @RequestMapping(value = "/auth/url", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getAuthURL() throws UnsupportedEncodingException {
        String authpath = "/jiacerapps/api/user/wechatAuthrize?redirect=";
        String suf = "&response_type=code&scope=snsapi_userinfo&state=@STATE@#wechat_redirect";
        StringBuffer sb = new StringBuffer();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
                .append("appid=").append(Constants.APPID)
                .append("&redirect_uri=")
                .append(URLEncoder.encode(Constants.HOSTNAME, UTF8))
                .append(URLEncoder.encode(authpath, UTF8))
                .append("@REDIRECT@")
                .append(suf);

        log.info("auth.url::::"+sb.toString());
        System.out.println(":::::::::::::::::::");
        System.out.println("auth.url::::"+sb.toString());
        System.out.println(":::::::::::::::::::");
        return JsonResult.success(sb.toString());
    }
}
