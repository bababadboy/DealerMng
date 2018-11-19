package com.bababadboy.dealermng.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

/*
属性	数据类型
约束
id	INT(自动生成)	AUTO_INCREMENT;PRIMARY KEY
商品编号	VARCHAR(50)	NOT NULL
名称	VARCHAR(50)	NOT NULL
库存 int >= 0
类别	VARCHAR(20)	NOT NULL
功能	VARCHAR(100)	NOT NULL
价格	DECIMAL(12,2)	NOT NULL
介绍	VARCHAR
图片	VARCHAR

 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    private String No;

    private String name;

    private int stock;

    @Version
    @Column(name = "version")
    private int version;

    private String type;
    private String functions;
    private double price;
    private String intro;
    private String images_link;

    /**** setter and getter ****/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImages_link() {
        return images_link;
    }

    public void setImages_link(String images_link) {
        this.images_link = images_link;
    }
}
