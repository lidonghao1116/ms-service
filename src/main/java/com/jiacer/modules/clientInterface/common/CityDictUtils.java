package com.jiacer.modules.clientInterface.common;

import com.jiacer.modules.mybatis.dao.AreasMapper;
import com.jiacer.modules.mybatis.entity.AreasEntity;
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
public class CityDictUtils implements InitializingBean {

    public static HashMap<Integer,String> CityCache = new HashMap<Integer, String>();

    @Autowired
    private AreasMapper cityDao;

    @Override
    public void afterPropertiesSet() throws Exception {

        List<HashMap<Integer,String>> kv = cityDao.getCityKV();

        for(HashMap<Integer,String> map : kv){
            CityCache.put(Integer.valueOf(map.get("area_code")),map.get("area_name"));
        }

        CityCache.put(999999, "");
    }

    public static String get(String code){

        if(StringUtils.isBlank(code)){
            return "";
        }
        return CityCache.get(Integer.valueOf(code));
    }
}
