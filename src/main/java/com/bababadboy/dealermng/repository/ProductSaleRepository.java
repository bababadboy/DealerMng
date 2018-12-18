package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.entity.ProductSaleInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author TwinkleN1
 */
public interface ProductSaleRepository extends JpaRepository<ProductSaleInfo,Long> {



    @Override
    List<ProductSaleInfo> findAll();
    ProductSaleInfo findProductSaleInfoById(Long id);

//    List<ProductSaleInfo> findProductSaleInfosByPaymentTime(Calendar payTime);

    List<ProductSaleInfo> findProductSaleInfosByPaymentTimeGreaterThanEqual(Calendar payTime);

    @Override
    <S extends ProductSaleInfo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends ProductSaleInfo> List<S> saveAll(Iterable<S> entities);

    @Override
    <S extends ProductSaleInfo> S saveAndFlush(S entity);

    @Override
    <S extends ProductSaleInfo> S save(S entity);
}
