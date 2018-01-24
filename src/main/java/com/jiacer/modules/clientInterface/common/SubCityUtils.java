package com.jiacer.modules.clientInterface.common;

import com.jiacer.modules.mybatis.dao.AreasMapper;
import com.jiacer.modules.mybatis.dao.SubAreasMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gaoyan on 03/07/2017.
 */
@Service
public class SubCityUtils implements InitializingBean {

    public static HashMap<String,String> CityCache = new HashMap<String, String>();

    @Autowired
    private SubAreasMapper cityDao;

    @Override
    public void afterPropertiesSet() throws Exception {

        List<HashMap<String,String>> kv = cityDao.getCityKV();

        for(HashMap<String,String> map : kv){
            CityCache.put(map.get("area_code"),map.get("area_name"));
        }

    }

    public static String get(String code){

        if(StringUtils.isBlank(code)){
            return "";
        }
        return CityCache.get(code);
    }
}
