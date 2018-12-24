package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Ash
 */
public interface OrderItemService {
    /**
     * 分页获取经销商订单信息
     * @param dealer 经销商信息
     * @return 订单分页
     */
    List<OrderItem> listOrdersByPage(Dealer dealer);

    List<OrderItem> listRecentOrders(Dealer dealer);
}
