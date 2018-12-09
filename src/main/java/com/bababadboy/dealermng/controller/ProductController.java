package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.ProductRepository;
//import com.sun.javafx.collections.MappingChange;
import com.bababadboy.dealermng.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    private final ProductServiceImpl productQueryService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductServiceImpl productQueryService) {
        this.productRepository = productRepository;
        this.productQueryService = productQueryService;
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
     * @param page 0
     * @param size 15
     * @return Page<Product>
     */
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public Page<Product> retrieveAllProducts(@RequestParam(value = "page", defaultValue = "0") Integer page ,
                                             @RequestParam(value = "size", defaultValue = "15") Integer size){
        return productQueryService.findProductNoCriteria(page,size);
    }

    @GetMapping(value = "/products/{id}")
    public Object retrieveProduct(@PathVariable("id") long id) {

        Optional<Product> product = productQueryService.retrieveProduct(id);
        return toJSON(product,"id");
    }

    @RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product p){

        Product savedProduct = productRepository.save(p);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/products/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateProduct(//TODO patch未完成
            @PathVariable long id, @RequestBody Map<String,Object> updates){

        return ResponseEntity.ok("Updated product successfully.");

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

        productQueryService.updateProduct(product,id);
        return ResponseEntity.noContent().build();
    }
}
