package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author wangxiaobin
 * @description: 实现ProductQueryService接口
 * @create 2018-12-03 下午11:31
 */
public class ProductQueryServiceImpl implements ProductQueryService{

    @Resource
    private ProductRepository productRepository;
    @Override
    public Page<Product> findProductNoCriteria(Integer page, Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC," id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    /**
     * 分页条件查询
     * @param page
     * @param size
     * @param product
     * @return
     */
    @Override
    public Page<Product> findProductCriteria(Integer page, Integer size, Product product) {

        Sort sort = new Sort(Sort.Direction.DESC," id");
        Pageable pageable = PageRequest.of(page, size, sort);
/*
        Page<Product> productPage = productRepository.findAll(new Specification<Product>(){
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.equal(root.get("no").as(String.class), product.getNo());
                Predicate p2 = criteriaBuilder.equal(root.get("name").as(String.class), product.getName());
                Predicate p3 = criteriaBuilder.equal(root.get("categories").as(String.class), product.getCategories());
                query.where(criteriaBuilder.and(p1,p2,p3));
                return query.getRestriction();
            }
        },pageable);*/
        return null;//TODO
    }
}
