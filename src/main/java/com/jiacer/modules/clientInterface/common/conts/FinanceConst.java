package com.jiacer.modules.clientInterface.common.conts;

/**
 * 订单状态
 * Created by gaoyan on 05/07/2017.
 */
public interface FinanceConst {

    /**
     * 收入支出类型
     */
    enum Direction{

        EXPENSE("1", "支出"),
        INCOME("0", "收入");

        private String code;
        private String desc;

        private Direction(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static Direction get(String code) {
            for (Direction pair : values()) {
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

        public void setDesc(String desc)  {
            this.desc = desc;
        }
    }



    /**
     * 流水类型
     */
    enum FlowType{

        ORDER_PAY("100", "订单支付"),
        REDPACK_GET("200", "收取红包");

        private String code;
        private String desc;

        private FlowType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static FlowType get(String code) {
            for (FlowType pair : values()) {
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

        public void setDesc(String desc)  {
            this.desc = desc;
        }
    }


    /**
     * 来源渠道
     */
    enum OriginChannel{

        JIACER_ONLINE_SCHOOL_WEB("01", "家策微课堂WEB"),
        JIACER_ONLINE_SCHOOL_APP("02", "家策微课堂APP"),
        JIACER_GOOD_SERVICE("10", "家策好服务");

        private String code;
        private String desc;

        private OriginChannel(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static OriginChannel get(String code) {
            for (OriginChannel pair : values()) {
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

        public void setDesc(String desc)  {
            this.desc = desc;
        }
    }
}
