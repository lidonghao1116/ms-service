package com.jiacer.modules.clientInterface.common.conts;

/**
 * 支付相关
 * Created by gaoyan on 10/07/2017.
 */
public interface GatewayConst {

    /**
     * 支付渠道
     */
    enum Gateway{
        SWIFTPASS("swiftpass", "威富通");

        private String code;
        private String desc;

        private Gateway(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static Gateway get(String code) {
            for (Gateway pair : values()) {
                if (pair.code.equals(code)) {
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


}
