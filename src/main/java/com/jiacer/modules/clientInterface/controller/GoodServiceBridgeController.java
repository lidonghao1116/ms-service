package com.jiacer.modules.clientInterface.controller;

import com.google.common.collect.Lists;
import com.jiacer.modules.clientInterface.common.anno.Auth;
import com.jiacer.modules.clientInterface.common.anno.Guest;
import com.jiacer.modules.common.utils.EnvConfig;
import com.jiacer.modules.common.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/gs/bridge")
public class GoodServiceBridgeController {

    private static final Logger log = LoggerFactory.getLogger(GoodServiceBridgeController.class);

    private static final String GS_PC_URL = EnvConfig.getInstance().getProperty("good.service.pc.url");
    private static final String GS_MOBILE_URL = EnvConfig.getInstance().getProperty("good.service.mobile.url");

    @Autowired
    private RestTemplate restTemplate;


    /**
     * showMore:obj.showMore,
     pageNumber : pageNumber,
     pageSize : pageSize,
     serviceType : serviceType,
     privince:privince
     * @return
     */
    @RequestMapping(value = "/job", method = RequestMethod.GET)
    @Guest
    @ResponseBody
    public Object getJob(String showMore, String pageNumber, String pageSize, String serviceType, String privince,
    		String age,String salaryType,String salary,String city){
        Map<String,String> param = new HashMap<String,String>();
        param.put("showMore", showMore);
        param.put("pageNumber", pageNumber);
        param.put("pageSize", pageSize);
        param.put("serviceType", serviceType);
        param.put("privince", privince);
        param.put("city", city);
        param.put("age", age);
        param.put("salaryType", salaryType);
        param.put("salary", salary);

        String url = GS_PC_URL+"/custServer/queryJob";

        log.info(url);
        Object obj = restTemplate.getForObject(encodeURLParam(url,param), Object.class);

        return obj;

    }

    @RequestMapping(value = "/province", method = RequestMethod.GET)
    @Guest
    @ResponseBody
    public Object getProvince(){
        String url = GS_MOBILE_URL+"/common/getAreaData/c001";
        log.info(url);
        Object obj = restTemplate.getForObject(url, Object.class);

        return obj;

    }

    @Guest
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ResponseBody
    public Object getCity(String pcode){
        Map<String,String> param = new HashMap<String,String>();
        String url = GS_MOBILE_URL+"/common/getAreaData/c002?pcode="+pcode;
        log.info(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Lists.newArrayList(Charset.forName("UTF-8")));
        HttpEntity req = new HttpEntity(headers);
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.GET,req, Object.class, param);

        return resp.getBody();

    }

    @Guest
    @RequestMapping(value = "/serviceType", method = RequestMethod.GET)
    @ResponseBody
    public Object getServiceType() {
        Map<String, String> param = new HashMap<String, String>();
        String url = GS_PC_URL + "/custServer/getServiceType";
        log.info(url);
        Object obj = restTemplate.getForObject(url, Object.class);
        return obj;

    }

    @Guest
    @RequestMapping(value = "/ageRange", method = RequestMethod.GET)
    @ResponseBody
    public Object getAgeRange(){
        Map<String,String> param = new HashMap<String,String>();
        String url = GS_PC_URL+"/custServer/getAgeRange";
        log.info(url);
        Object obj = restTemplate.getForObject(url, Object.class);
        return obj;

    }

    @Guest
    @RequestMapping(value = "/serviceIncome", method = RequestMethod.GET)
    @ResponseBody
    public Object getServiceIncome(){
        Map<String,String> param = new HashMap<String,String>();
        String url = GS_PC_URL+"/custServer/getServiceIncome";
        log.info(url);
        Object obj = restTemplate.getForObject(url, Object.class);
        return obj;

    }


    private String encodeURLParam(String url, Map<String,String> param){
        if(StringUtils.isBlank(url) || param == null){
            return url;
        }
        url += "?";
        for(Map.Entry<String,String> set : param.entrySet()){
            if(StringUtils.isBlank(set.getValue())){
                continue;
            }
            url = url + set.getKey()+"="+set.getValue() + "&";
        }

        return url;
    }
}
