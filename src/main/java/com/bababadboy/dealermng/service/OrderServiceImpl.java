package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ash
 */
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


}
