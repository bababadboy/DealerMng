package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
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

    @GetMapping(value = "/products")
    public List<Product> retrieveAllProducts() {
        List<Product> list = productRepository.findAll();
        /*for (Iterator<Product> it = list.iterator(); it.hasNext();){
            Product p = it.next();
            System.out.println(p.toString());
            System.out.println("p是:"+JSON.toJSONString(p));

        }*/
        String string = JSON.toJSONString(list);
        return list;

    }

    @GetMapping(value = "/products/{id}")
    public Product retrieveProduct(@PathVariable("id") long id) {

        Product product = productRepository.findById(id);
        // Product product2 = new Product("3","product2",41,1,"床","睡觉的床",233.33,"https://s1.ax1x.com/2018/11/20/F9MJJK.jpg");
        System.out.println(JSON.toJSON(product));
        System.out.println(product.toString());

        return product;
    }

    @RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product p){

        Product savedProduct = productRepository.save(p);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

        Product p = productRepository.findById(id);

        p.setId(id);
        productRepository.save(p);

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
