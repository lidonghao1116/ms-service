package com.jiacer.modules.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by gaoyan on 20/07/2017.
 */
public class JiacerMappingJacksonHttpMessageConverter extends ObjectMapper {

    public JiacerMappingJacksonHttpMessageConverter() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getDefault());
        this.setDateFormat(format);
    }

}
