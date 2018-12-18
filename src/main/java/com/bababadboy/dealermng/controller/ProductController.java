package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.ProductRepository;
//import com.sun.javafx.collections.MappingChange;
import com.bababadboy.dealermng.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.*;

/**
 * 描述:
 * Product控制器
 *
 * @author wangxiaobin
 */
@Transactional
@RestController
public class ProductController{
    private final ProductRepository productRepository;
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductServiceImpl productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    /**
     * json过滤器:自定义想要过滤掉的属性
     */
    private static JSON toJSON(Object o,String... excludeKeys){

        List<String> excludesList = Arrays.asList(excludeKeys);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().addAll(excludesList);
        return JSON.parseObject(JSON.toJSONString(o,filter));
    }

    /**
     * 产品列表分页查询
     */
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public Object retrieveAllProducts(@RequestParam(value = "page", defaultValue = "0") Integer page ,
                                             @RequestParam(value = "size", defaultValue = "15") Integer size){
        return JSON.toJSON(productService.findProductNoCriteria(page,size).getContent());
    }

    /**
     * 根据产品编号"no"查询商品详情
     */
    @GetMapping(value = "/products/{no}")
    public Object retrieveProduct(@PathVariable("no") String no) {
        Optional<Product> product = productService.retrieveProduct(no);
        return JSON.toJSON(product);
    }

    /**
     * 根据产品编号"no"删除商品
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')") // 只有admin才能有删除权限
    @RequestMapping(value = "/products/{no}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String no) {
        productService.deleteProduct(no);
    }

    /**
     * 增加商品
     */
    @RequestMapping(value = "/products", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@RequestBody Product p){

        Product savedProduct = productRepository.save(p);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/products/{no}",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateProduct(//TODO patch未完成
            @PathVariable String no, @RequestBody Map<String,Object> updates){

        return ResponseEntity.ok("Updated product successfully.");

    }

    /**
     * 根据no修改商品信息
     */
    @PutMapping("/products/{no}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable String no) {

        productService.updateProduct(product,no);
        return ResponseEntity.noContent().build();
    }
}
