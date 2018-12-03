package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ash
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * 查询指定经销商所有订单
     * @param pageable 分页信息
     * @param dealer 经销商信息
     * @return 订单分页
     */
    Page<OrderItem> findAllByDealer(Pageable pageable, Dealer dealer);
}
