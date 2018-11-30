package com.bababadboy.dealermng.entity;
import org.hibernate.annotations.Type;
import java.math.BigDecimal;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author iYmz
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
    private Calendar orderTime;

    //发货时间
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar deliveryTime;

    //付款时间
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar paymentTime;

    //订单完成时间
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar orderFinishTime;

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

    public Calendar getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Calendar orderTime) {
        this.orderTime = orderTime;
    }

    public Calendar getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Calendar deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Calendar getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Calendar paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Calendar getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Calendar orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
