package com.jiacer.modules.wechat.message;

import com.jiacer.modules.clientInterface.common.conts.Constants;
import com.jiacer.modules.common.utils.JsonUtils;
import com.jiacer.modules.mybatis.dao.UserSubscribeMapper;
import com.jiacer.modules.mybatis.model.UserSubscrbes;
import com.jiacer.modules.wechat.message.config.WechatConfig;
import com.jiacer.modules.wechat.message.model.JobMessageModel;
import com.jiacer.modules.wechat.message.model.PushModel;
import com.jiacer.modules.wechat.message.util.OKHttpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 门店助手需求发布
 */
@Service
public class MessageApplication implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageApplication.class);

    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserSubscribeMapper userSubscribeMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();//请使用valueSerializer
        String itemValue = (String)redisTemplate.getValueSerializer().deserialize(body);

        JobMessageModel job = JsonUtils.fromJson(itemValue, JobMessageModel.class);

        log.info(String.format("get message [%s] " , message));
        if(StringUtils.isBlank(itemValue) || job == null){
            return;
        }
        UserSubscrbes ts = new UserSubscrbes();
        ts.setChannel(WechatConfig.Channel.JOB_MS_PUBLISH.getChannel());
        ts.setSubject(job.getServiceType());
        ts.setProvince(job.getProvince());
        ts.setCity(job.getCity());
        ts.setDistrict(job.getDistrict());
        ts.setSubject(job.getServiceType());
        List<UserSubscrbes> lists = userSubscribeMapper.getJobSubscribeBySubject(ts);
        for(UserSubscrbes sub : lists){
            job.setOpenid(sub.getOpenid());
            PushModel data = new PushModel();
            data.setTouser(job.getOpenid());
            data.setTemplateId(WechatConfig.Template.NEW_JOB_TEMPLATE.getId());
            data.setUrl(Constants.HOSTNAME + "/pages/job.html");
            data.addData("first", job.getTitle()+"\n", null)
                    .addData("keyword1", job.getServiceDate(), "#173177")
                    .addData("keyword2", job.getAddress(), "#173177")
                    .addData("keyword3", job.getSalary(), "#173177")
                    .addData("keyword4", job.getDescription(), "#173177");

            String token = null;
            try {
                token = OKHttpUtils.get(WechatConfig.Api.GET_TOKEN.getValue(Constants.APPID, Constants.APPSECRET));
            } catch (IOException e) {
                log.error("token is null : " + e.getMessage());
            }
            if (StringUtils.isBlank(token)) {
                continue;
            }
            try {
                push(data);
            } catch (Exception e) {
                log.error("push error: " + e.getMessage());
            }
        }

    }


    public void push(PushModel data) throws Exception {
        log.info("push message: "+ JsonUtils.toJson(data));

        String token = OKHttpUtils.get(WechatConfig.Api.GET_TOKEN.getValue(Constants.APPID, Constants.APPSECRET));
        if (StringUtils.isBlank(token)) {
            return;
        }
        Map<String, String> tokenEntity = JsonUtils.fromJson(token, Map.class);
        String url = WechatConfig.Api.POST_MESSAGE.getValue(tokenEntity.get("access_token"));
        String content = JsonUtils.toJson(data);
        String result = OKHttpUtils.post(url, content);
        log.info("result: " + result);
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
