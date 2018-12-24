package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.OrderDetail;
import com.bababadboy.dealermng.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ash
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * 查询指定经销商所有订单
     * @param dealer 经销商信息
     * @return 订单分页
     */
    List<OrderItem> findAllByDealer(Dealer dealer);

    /**
     * 查询指定经销商的最近订单信息
     */
    List<OrderItem> findTop10ByDealerOrderByPaidAtDesc(Dealer dealer);

    List<OrderItem> findOrderItemsBypaidAtBetween(Date begin, Date end);

    List<OrderItem> findOrderItemsBypaidAtGreaterThanEqual(Date payTime);
}
