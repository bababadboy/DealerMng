package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ash
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
