package com.jiacer.modules.common.utils;

import com.jiacer.modules.clientInterface.common.ResultCode;
import com.jiacer.modules.clientInterface.common.conts.ErrorCode;
import com.jiacer.modules.common.persistence.ModelSerializable;

/**
 * 封装返回数据
 * @author hzp
 *
 */
public class JsonResult implements ModelSerializable{

    private boolean success;
    private Integer code;
    private String msg;
    private String toUrl;
    private Integer page;
    private Integer totalPage;
    private Integer TotalData;
    private Object jsonData;

    public JsonResult() {
    }

    public JsonResult(boolean success,Integer code, String msg, Object data) {
    	this.code=code;
        this.success = success;
        this.msg = msg;
        this.jsonData = data;
    }
    
    public JsonResult(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.jsonData = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getJsonData() {
        return jsonData;
    }

    public void setJsonData(Object jsonData) {
        this.jsonData = jsonData;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalData() {
        return TotalData;
    }

    public void setTotalData(Integer totalData) {
        TotalData = totalData;
    }

    public static JsonResult success(Object jsonData) {
        return new JsonResult(true,ResultCode.SUCCESS, ResultCode.getMsg(ResultCode.SUCCESS), jsonData);
    }

    public static JsonResult success(Object jsonData, Integer page, Integer totalPage, Integer totalCount) {
        JsonResult json = new JsonResult(true,ResultCode.SUCCESS, ResultCode.getMsg(ResultCode.SUCCESS), jsonData);
        json.setPage(page);
        json.setTotalData(totalCount);
        json.setTotalPage(totalPage);
        return json;
    }

    public static JsonResult failure(Integer code) {
        return new JsonResult(false,code, ResultCode.getMsg(code), null);
    }

    public static JsonResult failure(Integer code, String message) {
        return new JsonResult(false,code, message, null);
    }

    public static JsonResult failure(ErrorCode ec) {
        return new JsonResult(false,ec.getKey(), ec.getValue(), null);
    }
}
