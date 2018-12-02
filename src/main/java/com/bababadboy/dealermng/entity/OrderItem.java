package com.bababadboy.dealermng.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 * @author Ash
 */
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 订单编号
    @Column(nullable = false, unique = true)
    private String orderNo;

    // 经销商编号
    @ManyToOne(optional = false)
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    // 订单状态
    @Column(nullable = false)
    private String orderStatus;

    // 订单结算方式
    @Column(nullable = false)
    private String orderPaymentMethod;

    // 商品数量
    @Column(nullable = false)
    private Long productQuantity;

    // 商品总价
    @Column(precision = 10, scale = 2, nullable = false)
    private Double productTotalPrice;

    // 订单金额
    @Column(precision = 10, scale = 2, nullable = false)
    private Double orderTotalPrice;

    // 联系电话
    @Column(nullable = false)
    private String phone;

    // 收货地址
    @Column(nullable = false)
    private String address;

    // 快递单号
    private String expressNumber;

    // 下单时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date orderedAt;

    // 付款时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date paidAt;

    // 发货时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredAt;

    // 订单完成时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    // 订单备注
    private String note;

    protected OrderItem() {}

    public OrderItem(String orderNo, Dealer dealer, String orderStatus, String orderPaymentMethod, Long productQuantity, Double productTotalPrice, Double orderTotalPrice, String phone, String address, String expressNumber, Date orderedAt, Date paidAt, Date deliveredAt, Date completedAt, String note) {
        this.orderNo = orderNo;
        this.dealer = dealer;
        this.orderStatus = orderStatus;
        this.orderPaymentMethod = orderPaymentMethod;
        this.productQuantity = productQuantity;
        this.productTotalPrice = productTotalPrice;
        this.orderTotalPrice = orderTotalPrice;
        this.phone = phone;
        this.address = address;
        this.expressNumber = expressNumber;
        this.orderedAt = orderedAt;
        this.paidAt = paidAt;
        this.deliveredAt = deliveredAt;
        this.completedAt = completedAt;
        this.note = note;
    }

    /* getter and setter */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPaymentMethod() {
        return orderPaymentMethod;
    }

    public void setOrderPaymentMethod(String orderPaymentMethod) {
        this.orderPaymentMethod = orderPaymentMethod;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
