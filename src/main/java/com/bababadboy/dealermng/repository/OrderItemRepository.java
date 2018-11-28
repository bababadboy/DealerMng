package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ash
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
