package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.entity.Product;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

/**
 * @author wangxiaobin
 */
public interface ProductService {

    Page<Product> findProductNoCriteria(Integer page,Integer size); // 非条件查询

    Page<Product> findProductCriteria(Integer page,Integer size,Product product);   // 条件查询

    List<Product> retrieveAllProducts();    // 查询产品列表

    Optional<Product> retrieveProduct(long id); // 查询产品详情

    void updateProduct( Product product, long id);    // 产品更新
}
