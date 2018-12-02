package com.bababadboy.dealermng.pojo;

import javax.persistence.Embeddable;

/**
 * 地址类
 * @author Ash
 * @date 2018/11/28 23:33
 */
@Embeddable
public class Address {

    private String province;

    private String city;

    private String district;

    public Address() {
    }

    public Address(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
