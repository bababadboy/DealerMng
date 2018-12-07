package com.bababadboy.dealermng.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dealer_warehouse")
public class DealerWarehouse implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    //经销商信息
    @ManyToOne(optional = false)
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;


    @Column(name = "warehouse_no",nullable = false,unique = true)
    private String warehouse_no;

    @Column(name = "address",nullable = false)
    private String address;

    public DealerWarehouse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String getWarehouse_no() {
        return warehouse_no;
    }

    public void setWarehouse_no(String warehouse_no) {
        this.warehouse_no = warehouse_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
