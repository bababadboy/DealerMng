package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.OrderDetail;
import com.bababadboy.dealermng.entity.OrderItem;
import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.repository.OrderDetailRepository;
import com.bababadboy.dealermng.repository.OrderItemRepository;
import com.bababadboy.dealermng.repository.ProductRepository;
import com.bababadboy.dealermng.service.OrderItemService;
import com.bababadboy.dealermng.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Ash
 */
@CrossOrigin(origins = "http://localhost:8080" )
@Controller
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderItemRepository orderItemRepository;

    private final OrderItemService orderItemService;

    private final OrderDetailRepository orderDetailRepository;

    private final DealerRepository dealerRepository;

    private final ProductRepository productRepository;

    private final UserService userService;

    @Autowired
    public OrderController(OrderItemRepository orderItemRepository, OrderItemService orderItemService, DealerRepository dealerRepository, ProductRepository productRepository, OrderDetailRepository orderDetailRepository, UserService userService) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
        this.dealerRepository = dealerRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listOrdersByDealerId(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam("dealerId") Long dealerId) {
        Optional<Dealer> dealer = dealerRepository.findById(dealerId);
        if (!dealer.isPresent()) {
            return new ResponseEntity<>("dealer not found", HttpStatus.BAD_REQUEST);
        }
        Page<OrderItem> list = orderItemService.listOrdersByPage(page, 10, dealer.get());
        return new ResponseEntity<>(JSON.toJSON(list.getContent()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<?> showOrder(@PathVariable("orderId") Long orderId, HttpServletRequest req) {
        Optional<OrderItem> order = orderItemRepository.findById(orderId);
        if (!order.isPresent()) {
            return new ResponseEntity<>("order not found", HttpStatus.BAD_REQUEST);
        }
        User user = userService.whoami(req);
        if (!user.getDealer().getId().equals(order.get().getDealer().getId())) {
            return new ResponseEntity<>("dealer not correct", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(JSON.toJSON(order.get()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody OrderItem orderItem, HttpServletRequest req) {
        User user = userService.whoami(req);
        List<OrderDetail> orderDetails = new ArrayList<>(orderItem.getOrderDetails().size());
        orderItem.setOrderStatus(0);
        orderItem.setOrderedAt(new Date());
        orderItem.setDealer(user.getDealer());
        double totalPrice = 0.0D;
        for (OrderDetail od : orderItem.getOrderDetails()) {
            Optional<Product> product = productRepository.findByNo(od.getProduct().getNo());
            double price = od.getAmount() * product.get().getPrice();
            od.setTotalMoney(price);
            od.setProduct(product.get());
            totalPrice += price;
            orderDetails.add(od);
        }
        orderItem.setOrderDetails(orderDetails);
        orderItem.setOrderTotalPrice(totalPrice);
        OrderItem order = orderItemRepository.save(orderItem);
        for(OrderDetail od : orderDetails) {
            od.setOrderItem(order);
        }
        orderDetailRepository.saveAll(orderDetails);
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", order.getId());
        map.put("orderedAt", order.getOrderedAt());
        map.put("orderTotalPrice", order.getOrderTotalPrice());
        return new ResponseEntity<>(JSON.toJSON(map), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody Map<String, Object> map) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (!orderItem.isPresent()) {
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(orderItem.get());
        for (String key : map.keySet()) {
            jsonObject.put(key, map.get(key));
        }
        orderItemRepository.save(JSON.toJavaObject(jsonObject, OrderItem.class));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 最近订单接口 by wxb
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/recent",method = RequestMethod.GET)
    public ResponseEntity<?> recentOrder(HttpServletRequest req) {
        User user = userService.whoami(req);
        Dealer dealer = user.getDealer();
        List<OrderItem> list = orderItemService.listRecentOrders(dealer);
        return new ResponseEntity<>(JSON.toJSON(list), HttpStatus.OK);

    }
}
