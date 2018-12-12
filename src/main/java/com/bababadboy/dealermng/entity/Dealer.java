package com.bababadboy.dealermng.entity;

import com.bababadboy.dealermng.pojo.Address;
import com.bababadboy.dealermng.pojo.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 经销商实体类
 * @author Ash
 */
@Entity
@Table(name = "dealer")
public class Dealer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 经销商姓名
    @Column(nullable = false)
    private String name;

    // 经销商性别
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // 经销商电话
    @Column(nullable = false, unique = true)
    private String phone;

    // 经销商联系地址
    @Column(nullable = false)
    @Embedded
    private Address address;

    // 经销商信用等级
    @Column(nullable = false)
    private Integer credit;

    // 经销商负责地区
    @Column(nullable = false)
    private String area;

    // 经销商注册时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date registerAt;

    // 经销商合同到期时间
    @Column(nullable = false)
    private Date expiredAt;

    // 备注信息
    @Lob
    private String note;

    /**
     * Constructor
     */
    public Dealer() {
    }

    public Dealer(String name, Gender gender, String phone, Address address, Integer credit, String area, Date registerAt, Date expiredAt, String note) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.credit = credit;
        this.area = area;
        this.registerAt = registerAt;
        this.expiredAt = expiredAt;
        this.note = note;
    }

    /**
     * getter and setter
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(Date registerAt) {
        this.registerAt = registerAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
