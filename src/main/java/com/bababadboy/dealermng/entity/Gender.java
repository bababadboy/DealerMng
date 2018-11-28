package com.bababadboy.dealermng.entity;

/**
 * 性别枚举
 * @author Ash
 * @date 2018/11/27 21:28
 */
public enum Gender {
    /**
     * 性别字段
     */
    male("男"),
    female("女");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
