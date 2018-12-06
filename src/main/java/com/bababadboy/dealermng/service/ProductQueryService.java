package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.entity.Product;
import org.springframework.data.domain.Page;

/**
 * @author wangxiaobin
 * @description: ProductQueryService
 * @create 2018-12-03 下午11:28
 */
public interface ProductQueryService {

    Page<Product> findProductNoCriteria(Integer page,Integer size); // 非条件查询

    Page<Product> findProductCriteria(Integer page,Integer size,Product product);   // 条件查询
}
