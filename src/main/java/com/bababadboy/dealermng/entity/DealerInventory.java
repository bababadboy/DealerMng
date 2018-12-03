package com.bababadboy.dealermng.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dealer_inventory")
public class DealerInventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    //经销商仓库
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "dealer_warehouse_id")
    private DealerWarehouse dealerWarehouse;

    //商品信息

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "product")
    private  Product product;

 //   private  String product;
    //库存量
    @Column(name = "stocks",nullable = false)
    private short stocks;

    //库存提醒上下限
    //最大库存
    @Column(name = "max_stocks",nullable = false)
    private short maxStocks;

    //最小库存
    @Column(name = "min_stocks",nullable = false)
    private short minStocks;

    public DealerInventory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DealerWarehouse getDealerWarehouse() {
        return dealerWarehouse;
    }

    public void setDealerWarehouse(DealerWarehouse dealerWarehouse) {
        this.dealerWarehouse = dealerWarehouse;
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
