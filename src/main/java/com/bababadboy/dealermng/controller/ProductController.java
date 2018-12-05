package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.ProductRepository;
//import com.sun.javafx.collections.MappingChange;
import com.bababadboy.dealermng.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.spring.web.json.Json;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

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
    /**
     * 推荐注入方式，而非
     * "@Autowired"
     * private ProductRepository productRepository;
     */
    @Autowired
    public ProductController(ProductRepository productRepository, ProductServiceImpl productQueryService) {
        this.productRepository = productRepository;
        this.productQueryService = productQueryService;
    }

    /**
     * 产品列表分页查询
     * @param page 0
     * @param size 15
     * @return Page<Product>
     */
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public Page<Product> retrieveAllProducts(@RequestParam(value = "page", defaultValue = "0") Integer page ,
                                             @RequestParam(value = "size", defaultValue = "15")  Integer size){

        /*
        Sort sort = new Sort(Sort.Direction.DESC," id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page <Product> p = productQueryService.findProductNoCriteria(page,size);
        */
        return productQueryService.findProductNoCriteria(page,size);
    }

    @GetMapping(value = "/products/{id}")
    public Object retrieveProduct(@PathVariable("id") long id) {

        Optional<Product> product = productQueryService.retrieveProduct(id);
        return JSON.toJSON(product);
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

//        productRepository.save(updates);
        return ResponseEntity.ok("Updated product successfully.");

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

        productQueryService.updateProduct(product,id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String demo() {
        /*String[] strings = new String[3];
        strings[0] = "demo1";
        strings[1] = "demo2";
        strings[2] = "demo3";
        String json = JSON.toJSONString(strings);
        return json;*/

        return "test";
    }
}
