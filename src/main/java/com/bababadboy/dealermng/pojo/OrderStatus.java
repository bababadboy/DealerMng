package com.bababadboy.dealermng.pojo;

/**
 * 订单状态枚举类
 * @author Ash
 * @date 2018/12/6 16:20
 */
public enum OrderStatus {
    /**
     * 订单状态码
     */
    unPaid("未付款"),
    Paid("已付款"),
    companyConfirmed("集团已确认"),
    delivered("已发货"),
    signed("已签收"),
    orderDone("交易完成"),
    salesReturnRequest("退货申请中"),
    salesReturning("退货中"),
    salesReturned("已退货"),
    orderCanceled("交易取消");


    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

