package com.bababadboy.dealermng.pojo;

import javax.persistence.Embeddable;

/**
 * 地址类
 * @author Ash
 * @date 2018/11/28 23:33
 */
@Embeddable
public class Address {

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String district;

    // 街道
    private String street;

    // 详情
    private String details;

    public Address() {
    }

    public Address(String province, String city, String district, String street, String details) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.details = details;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
