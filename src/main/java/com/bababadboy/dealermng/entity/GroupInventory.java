package com.bababadboy.dealermng.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "group_inventory")
public class GroupInventory implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_warehouse_id")
    private GroupWarehouse groupWarehouse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private  Product product;

    @Column(name = "stocks",nullable = false)
    private short stocks;

    @Column(name = "max_stocks",nullable = false)
    private short maxStocks;

    @Column(name = "min_stocks",nullable = false)
    private short minStocks;

    public GroupInventory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupWarehouse getGroupWarehouse() {
        return groupWarehouse;
    }

    public void setGroupWarehouse(GroupWarehouse groupWarehouse) {
        this.groupWarehouse = groupWarehouse;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public short getStocks() {
        return stocks;
    }

    public void setStocks(short stocks) {
        this.stocks = stocks;
    }

    public short getMaxStocks() {
        return maxStocks;
    }

    public void setMaxStocks(short maxStocks) {
        this.maxStocks = maxStocks;
    }

    public short getMinStocks() {
        return minStocks;
    }

    public void setMinStocks(short minStocks) {
        this.minStocks = minStocks;
    }
}
