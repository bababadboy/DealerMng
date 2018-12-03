package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.ProductRepository;
//import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 描述:
 * Product控制器
 *
 * @author wangxiaobin
 * @date 2018-11-19 下午7:18
 */
@Transactional
@RestController
public class ProductController{

    @Autowired
    private ProductRepository productRepository;
    @Autowired



    @GetMapping(value = "/products")
    public Object retrieveAllProducts() {
        List<Product> list = productRepository.findAll();
        /*for (Iterator<Product> it = list.iterator(); it.hasNext();){
            Product p = it.next();
            System.out.println(p.toString());
            System.out.println("p是:"+JSON.toJSONString(p));

        }*/
        Object object = JSON.toJSON(list);
        return object;

    }

    @GetMapping(value = "/products/{id}")
    public Object retrieveProduct(@PathVariable("id") long id) {

        Optional<Product> product = productRepository.findById(id);
        Object object = JSON.toJSON(product);
        return object;
    }

    @RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product p){

        Product savedProduct = productRepository.save(p);
        /*
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();
        */
        return ResponseEntity.ok("Save product successfully.");
    }


    @RequestMapping(value = "/produces/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateProduct(
            @PathVariable long id, @RequestBody Map<String,Object> updates){

//        productRepository.save(updates);
        return ResponseEntity.ok("Updated product successfully.");

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

        Optional<Product> p = productRepository.findById(id);

        if (p.isPresent()){
            p.get().setId(id);
        }
        productRepository.save(p.get());

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
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
