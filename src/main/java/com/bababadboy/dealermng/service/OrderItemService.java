package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.OrderItem;
import org.springframework.data.domain.Page;

/**
 * @author Ash
 */
public interface OrderItemService {
    /**
     * 分页获取经销商订单信息
     * @param page 页码
     * @param size 每页数量
     * @param dealer 经销商信息
     * @return 订单分页
     */
    Page<OrderItem> listOrdersByPage(Integer page, Integer size, Dealer dealer);
}
