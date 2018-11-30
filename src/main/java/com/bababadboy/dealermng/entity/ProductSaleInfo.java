package com.bababadboy.dealermng.entity;
import org.hibernate.annotations.Type;
import java.math.BigDecimal;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author TwinkleN1
 */
@Entity
@Table(name = "product_sale_info")
public class ProductSaleInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private  Long id;

    //交易编号
    @Column(nullable = false,unique = true)
    private String tradeId;

    //经销商编号
    @ManyToOne(optional = false)
    @JoinColumn(name = "dealer_id")
    private  Dealer dealer;

    //顾客编号
    @Column(nullable = false)
    private  Long customerId;

    //销售状态码
    @Column(nullable = false)
    private  int saleStatusCode;

    //销售结算状态码
    @Column(nullable = false)
    private  int salePaymentStatusCode;

    //销售商品数量
    @Column(nullable = false)
    private int productNums;

    //商品总价
    @Column(precision =10,scale = 2,nullable = false)
    @Type(type  = "big_decimal")
    private  BigDecimal totalPrice;

    //订单实付金额
    @Column(precision =10,scale = 2,nullable = false)
    @Type(type  = "big_decimal")
    private  BigDecimal orderAmount;


    //下单时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date orderTime;

    //发货时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryTime;

    //付款时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentTime;

    //订单完成时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderFinishTime;

    //备注
    private String comment;

    /* entity init */
    public ProductSaleInfo() {
    }

    /* geter and setter */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getSaleStatusCode() {
        return saleStatusCode;
    }

    public void setSaleStatusCode(int saleStatusCode) {
        this.saleStatusCode = saleStatusCode;
    }

    public int getSalePaymentStatusCode() {
        return salePaymentStatusCode;
    }

    public void setSalePaymentStatusCode(int salePaymentStatusCode) {
        this.salePaymentStatusCode = salePaymentStatusCode;
    }

    public int getProductNums() {
        return productNums;
    }

    public void setProductNums(int productNums) {
        this.productNums = productNums;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
