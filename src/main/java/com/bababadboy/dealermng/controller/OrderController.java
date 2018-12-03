package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.OrderItem;
import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.repository.OrderItemRepository;
import com.bababadboy.dealermng.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @author Ash
 */
@Controller
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderItemRepository orderItemRepository;

    private final OrderItemService orderItemService;

    private final DealerRepository dealerRepository;

    @Autowired
    public OrderController(OrderItemRepository orderItemRepository, OrderItemService orderItemService, DealerRepository dealerRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
        this.dealerRepository = dealerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listOrdersByDealerId(@RequestParam("page") Integer page,
                                        @RequestParam("dealerId") Long dealerId) {
        Optional<Dealer> dealer = dealerRepository.findById(dealerId);
        if (!dealer.isPresent()) {
            return new ResponseEntity<>("dealer not found", HttpStatus.BAD_REQUEST);
        }
        Page<OrderItem> list = orderItemService.listOrdersByPage(page, 10, dealer.get());
        return new ResponseEntity<>(JSON.toJSON(list.getContent()), HttpStatus.OK);
    }
}
