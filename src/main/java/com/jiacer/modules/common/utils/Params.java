package com.jiacer.modules.common.utils;

/**
 * 拼接URL请求的参数
 */
public class Params {
    private StringBuffer params=new StringBuffer();

    public Params add(String key,Object value){
        if(params.length()>0){
            params.append("&");
        }
        params.append(key).append("=");
        if(value==null){
            params.append("");
        }else{
            params.append(String.valueOf(value));
        }
        return this;
    }

    public String toString(){
        return params.toString();
    }

}