package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface ProductSaleOrderRepository extends JpaRepository<OrderDetail,Long> {
    @Override
    List<OrderDetail> findAll();

    @Override
    Optional<OrderDetail> findById(Long aLong);

    OrderDetail findOrderDetailById(Long aLong);
}
