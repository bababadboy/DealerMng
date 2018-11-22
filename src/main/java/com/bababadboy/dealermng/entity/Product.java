package com.bababadboy.dealermng.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String no;

    private String name;

    private int stock;

    @Version
    @Column(name = "version")
    private int version;

    private String type;
    private String descrption;
    private double price;

    @Column(name = "image_src")
    private String imageSrc;

    public Product() {
    }

    public Product(String no, String name, int stock, int version, String type, String descrption, double price, String imageSrc) {
        this.no = no;
        this.name = name;
        this.stock = stock;
        this.version = version;
        this.type = type;
        this.descrption = descrption;
        this.price = price;
        this.imageSrc = imageSrc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", version=" + version +
                ", type='" + type + '\'' +
                ", descrption='" + descrption + '\'' +
                ", price=" + price +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }
    /**** setter and getter ****/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
