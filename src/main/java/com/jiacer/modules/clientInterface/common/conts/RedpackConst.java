package com.jiacer.modules.clientInterface.common.conts;

/**
 * 红包相关枚举
 * Created by gaoyan on 30/06/2017.
 */
public interface RedpackConst {

    enum Type {
        NORMAL(1, "普通红包"),
        FISSION(2, "裂变红包");

        private Integer key;
        private String value;

        private Type(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public static Type get(String key) {
            for (Type pair : values()) {
                if (pair.key.equals(key)) {
                    return pair;
                }
            }
            return null;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    enum PayStatus {
        SENDING("01", "发送中"),
        SEND_FINISHED("02", "发送成功"),
        SEND_FAILURED("03", "发送失败");

        private String key;
        private String value;

        private PayStatus(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static PayStatus get(String key) {
            for (PayStatus pair : values()) {
                if (pair.key.equals(key)) {
                    return pair;
                }
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    enum Status {
        WAIT_SETTLEMENT("01", "待清算"),
        SETTLEMENT_FINISHED("02", "已清算");

        private String key;
        private String value;

        private Status(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static Status get(String key) {
            for (Status pair : values()) {
                if (pair.key.equals(key)) {
                    return pair;
                }
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    enum IsUsable {
        YES("1", "可用"),
        NO("0", "不可用");

        private String key;
        private String value;

        private IsUsable(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static IsUsable get(String key) {
            for (IsUsable pair : values()) {
                if (pair.key.equals(key)) {
                    return pair;
                }
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    enum ErrorCode {
        NO_AUTH("NO_AUTH", "用户账号异常，被拦截", "1", "0"),
        SENDNUM_LIMIT("SENDNUM_LIMIT", "该用户今日领取红包个数超过你在微信支付商户平台配置的上限", "0", "1"),
        ILLEGAL_APPID("ILLEGAL_APPID", "错误传入了app的appid", "0", "1"),
        MONEY_LIMIT("MONEY_LIMIT", "发送红包金额不再限制范围内", "0", "1"),
        SEND_FAILED("SEND_FAILED", "该红包已经发放失败", "0", "1"),
        FATAL_ERROR("FATAL_ERROR", "更换了openid，但商户单号未更新", "0", "1"),
        CA_ERROR("CA_ERROR", "请求携带的证书出错", "0", "1"),
        SIGN_ERROR("SIGN_ERROR", "签名错误  ", "0", "1"),
        SYSTEMERROR("SYSTEMERROR", "请求已受理，请稍后使用原单号查询发放结果", "0", "1"),
        XML_ERROR("XML_ERROR", "输入xml参数格式错误", "0", "1"),
        FREQ_LIMIT("FREQ_LIMIT", "受频率限制", "0", "1"),
        NOTENOUGH("NOTENOUGH", "账户余额不足", "1", "0"),
        OPENID_ERROR("OPENID_ERROR", "openid和appid不匹配", "0", "1"),
        MSGAPPID_ERROR("MSGAPPID_ERROR", "msgappid与主、子商户号的绑定关系校验失败", "0", "1"),
        PROCESSING("PROCESSING", "发红包流程正在处理", "0", "1"),
        PARAM_ERROR("PARAM_ERROR", "请求参数字段错误", "1", "1"),
        SUCCESS("SUCCESS", "支付成功", "0", "0");
        private String code;
        private String reason;
        private String isNeedRetry;
        private String isRemind;

        private ErrorCode(String code, String reason, String isNeedRetry, String isRemind) {
            this.code = code;
            this.reason = reason;
            this.isNeedRetry = isNeedRetry;
            this.isRemind = isRemind;
        }

        public static ErrorCode get(String key) {
            for (ErrorCode pair : values()) {
                if (pair.code.equals(key)) {
                    return pair;
                }
            }
            return null;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getIsNeedRetry() {
            return isNeedRetry;
        }

        public void setIsNeedRetry(String isNeedRetry) {
            this.isNeedRetry = isNeedRetry;
        }

        public String getIsRemind() {
            return isRemind;
        }

        public void setIsRemind(String isRemind) {
            this.isRemind = isRemind;
        }
    }
}
