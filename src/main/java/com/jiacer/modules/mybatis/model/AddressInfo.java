package com.jiacer.modules.mybatis.model;

import com.jiacer.modules.clientInterface.common.SubCityUtils;

public class AddressInfo {

    private String province;

    private String city;

    private String district;

    public String getProvince() {
        return province;
    }
    public String getProvinceValue() {
        if("0".equals(province)){
            return "不限";
        }
        return SubCityUtils.get(province);
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public String getCityValue() {
        if("0".equals(city)){
            return "不限";
        }
        return SubCityUtils.get(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public String getDistrictValue() {
        if("0".equals(district)){
            return "不限";
        }
        return SubCityUtils.get(district);
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
