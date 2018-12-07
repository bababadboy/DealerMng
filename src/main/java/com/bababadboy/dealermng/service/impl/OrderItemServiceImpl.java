package com.bababadboy.dealermng.service.impl;

import com.bababadboy.dealermng.repository.OrderItemRepository;
import com.bababadboy.dealermng.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ash
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

}
