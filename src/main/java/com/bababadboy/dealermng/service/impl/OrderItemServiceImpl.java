package com.bababadboy.dealermng.service.impl;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.OrderItem;
import com.bababadboy.dealermng.repository.OrderItemRepository;
import com.bababadboy.dealermng.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ash
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Page<OrderItem> listOrdersByPage(Integer page, Integer size, Dealer dealer) {
        Pageable pageable = PageRequest.of(page, size);
        return orderItemRepository.findAllByDealer(pageable, dealer);
    }

    @Override
    public List<OrderItem> listRecentOrders(Dealer dealer) {
        return orderItemRepository.findTop10ByDealerOrderByPaidAtDesc(dealer);
    }
}
