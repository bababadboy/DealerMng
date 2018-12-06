package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author wangxiaobin
 */

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,
        ProductRepoCustom<Product,Long> {

    @Override
    List<Product> findAll();

    @Override
    <S extends Product> S saveAndFlush(S entity);

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    <S extends Product> S save(S entity);
}
