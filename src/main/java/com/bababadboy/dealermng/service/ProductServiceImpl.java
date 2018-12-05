package com.bababadboy.dealermng.service;

import com.alibaba.fastjson.JSON;
import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 实现ProductQueryService接口
 * @author wangxiaobin
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;
    @Override
    public Page<Product> findProductNoCriteria(Integer page, Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findProductCriteria(Integer page, Integer size, Product product) {

/*        Sort sort = new Sort(Sort.Direction.DESC," id");
        Pageable pageable = PageRequest.of(page, size, sort);

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

    @Override
    public List<Product> retrieveAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Optional<Product> retrieveProduct(long id) {

        return productRepository.findById(id);
    }

    @Override
    public void updateProduct(Product product,long id) {

        Optional<Product> p = productRepository.findById(id);
        if (p.isPresent()){
            productRepository.save(product);
        }
    }
}
