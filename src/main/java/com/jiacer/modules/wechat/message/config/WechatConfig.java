package com.jiacer.modules.wechat.message.config;

import com.jiacer.modules.clientInterface.common.conts.Constants;

public interface WechatConfig {

    enum Channel {
        JOB_MS_PUBLISH("jiacedu.channel.job.publish",  "职位发布");

        private String channel;
        private String description;

        Channel(String channel, String description) {
            this.channel = channel;
            this.description = description;
        }

        public String getChannel() {
            return channel;
        }

    }
    enum Api {
        GET_TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"),
        POST_MESSAGE("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s"),
        POST_INDUSTRY("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s");

        private String value;

        Api(String value) {
            this.value = value;
        }

        public String getValue(String... param) {
            return String.format(value, param);
        }
    }

    enum Template{
        //prod
        NEW_JOB_TEMPLATE("D5rQGJ--j4NZAgiHA0KNSizH2iRzbzKRIDGMpN6C-EI",Constants.HOSTNAME+"/#/job/jobRelease/%s",  "职位发布提醒");


        //test
//        NEW_JOB_TEMPLATE("LY1bcozpqu9tgdMnXOsApmkVWNveUEJZ6KdNxDmMrRI", Constants.HOSTNAME+"/pages/job.html?jobId=%s",  "职位发布提醒");
    	
    	//uat
//    	NEW_JOB_TEMPLATE("fedqcMUEcQRwXkKl_Ho4JQkFvaH0YFY9Gc7WNo9R-wg", Constants.HOSTNAME+"/pages/job.html?jobId=%s",  "职位发布提醒");

        private String id;
        private String url;
        private String description;

        Template(String id, String url, String description) {
            this.id = id;
            this.url = url;
            this.description = description;
        }

        public String getId() {
            return id;
        }
        public String getUrl(String ... param) {
            return String.format(url, param);
        }
    }

}
