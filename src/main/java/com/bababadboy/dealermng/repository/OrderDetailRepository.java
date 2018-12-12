package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ash
 * @date 2018/12/7 0:06
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
