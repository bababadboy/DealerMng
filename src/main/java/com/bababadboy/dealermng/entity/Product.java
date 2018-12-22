package com.bababadboy.dealermng.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 产品实体类z
 * @author wangxiaobin
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(serialize = false)
    private Long id;

    @Column(unique = true)
    private String no;

    private String name;

    private int stock;

    @Version
    @Column(name = "version")
    private int version;

    private String categories;
    private String description;
    private double price;

    /*
    * 轮播图列表
    * */

    @ElementCollection(targetClass=String.class,fetch = FetchType.EAGER)
    @CollectionTable(name="carousel_images", joinColumns=@JoinColumn(name="id" , nullable=false))
    @Column(name="images_url")
    @OrderColumn(name="images_order")
    private List<String> carouselImages;

    /*@ElementCollection
    private List<String> imageUrl = new ArrayList<>();*/


    /*
    * 详情图列表
    * */

    @ElementCollection(targetClass=String.class,fetch = FetchType.EAGER)
    @CollectionTable(name="detail_images", joinColumns=@JoinColumn(name="id" , nullable=false))
    @Column(name="images_url")
    @OrderColumn(name="images_order")
    private List<String> detailImages;

    /*@ElementCollection
    private List<String> detailImages = new ArrayList<>();*/

    /**
     * 构造函数
     **/
    public Product() {
    }

    public Product(String no, String name, int stock, int version,
                   String categories, String description, double price,
                   List<String> carouselImages, List<String> detailImages) {
        this.no = no;
        this.name = name;
        this.stock = stock;
        this.version = version;
        this.categories = categories;
        this.description = description;
        this.price = price;
        this.carouselImages = carouselImages;
        this.detailImages = detailImages;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCarouselImages() {
        return carouselImages;
    }

    public void setCarouselImages(List<String> carouselImages) {
        this.carouselImages = carouselImages;
    }

    public List<String> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<String> detailImages) {
        this.detailImages = detailImages;
    }
}
