package com.jiacer.modules.clientInterface.common.conts;

import com.jiacer.modules.clientInterface.common.ActionUtil;

import javax.swing.*;

/**
 * Created by gaoyan on 14/07/2017.
 */
public interface RedisConst {

    enum Common {
        SMS_CODE("COM.JIACER.WX.COMMON.SMS_CODE.%s", "短信验证码"),
        CAPTCHA_CODE("COM.JIACER.WX.COMMON.CAPTCHA_CODE.%s", "图形验证码");

        private String key;
        private String desc;

        private Common(String key, String desc) {
            this.key = key;
            this.desc = desc;
        }

        public static Common get(String key) {
            for (Common pair : values()) {
                if (pair.key.equals(key)) {
                    return pair;
                }
            }
            return null;
        }

        public String getKey() {
            return String.format(this.key, ActionUtil.getUserOpenId());
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
