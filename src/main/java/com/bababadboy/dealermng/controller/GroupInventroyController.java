package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.bababadboy.dealermng.entity.GroupInventory;
import com.bababadboy.dealermng.entity.GroupWarehouse;
import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.repository.GroupInventoryReposity;
import com.bababadboy.dealermng.repository.GroupWarehouseReposity;
import com.bababadboy.dealermng.repository.ProductRepository;
import com.bababadboy.dealermng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@RestController
public class GroupInventroyController {
    @Autowired
    private GroupInventoryReposity groupInventoryReposity;
    @Autowired
    private GroupWarehouseReposity groupWarehouseReposity;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    private static JSON toJsons(Object o, String... excludeKeys) {
        List<String> excludes = Arrays.asList(excludeKeys);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().addAll(excludes);    //重点！！！
        return JSON.parseObject(JSON.toJSONString(o, filter));
    }

    @GetMapping(value = "/groupInventory")
    public Object retrieveAllInventory() {

        List<GroupInventory> list = groupInventoryReposity.findAll();
        List<Product> productList = productRepository.findAll();
        GroupWarehouse groupWarehouse = groupWarehouseReposity.getOne(1L);
        List<JSON> jsonList = new ArrayList<>();
        for (Product groupInventory : productList) {
            String warehouseId = groupWarehouse.getId().toString();
            String warehouseAddress = groupWarehouse.getAddress();
            String productNo = groupInventory.getNo();
            String productName = groupInventory.getName();
            Long stock = groupInventory.getStocks();
            String warehouseName = groupWarehouse.getWarehouseName();
            double price = groupInventory.getPrice();
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("warehouseAddress", warehouseAddress);
            jsonObject.put("warehouseName", warehouseName);
            jsonObject.put("productNo", productNo);
            jsonObject.put("productName", productName);
            jsonObject.put("price", price);
            jsonObject.put("warehouseId", warehouseId);
            jsonObject.put("stock", stock);
            jsonList.add(jsonObject);
        }
        return jsonList;

        // return list;
    }

    @GetMapping(value = "/groupInventoryPage")
    public Object retrievePagesInventory(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<GroupInventory> p = groupInventoryReposity.findAll(pageable);
        List<JSON> jsonList = new ArrayList<>();
        for(GroupInventory groupInventory : p)
        {
            String warehouseId = groupInventory.getGroupWarehouse().getId().toString();
            String warehouseAddress = groupInventory.getGroupWarehouse().getAddress();
            String productNo = groupInventory.getProduct().getNo();
            String productName = groupInventory.getProduct().getName();
            Long stock = groupInventory.getProduct().getStocks();
            String warehouseName = groupInventory.getGroupWarehouse().getWarehouseName();
            double price = groupInventory.getProduct().getPrice();
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("warehouseAddress", warehouseAddress);
            jsonObject.put("warehouseName", warehouseName);
            jsonObject.put("productNo", productNo);
            jsonObject.put("productName", productName);
            jsonObject.put("price", price);
            jsonObject.put("warehouseId", warehouseId);
            jsonObject.put("stock", stock);
            jsonList.add(jsonObject);
        }
        return jsonList;
    }
@RequestMapping(value = "/groupInventory", method = RequestMethod.POST)
    public  void  updateInventory(@RequestBody JSONObject jsonObject){
    String productNo=  jsonObject.getString("productNo");
    Long stocks=  jsonObject.getLong("stock");
    Long warehouseId=  jsonObject.getLong("warehouseId");

    //Combined query inventory entity by  warehouseId and productId
    //GroupInventory groupInventory = groupInventoryReposity.findGroupInventoryByGroupWarehouseAndProduct(warehouseId,productId );
    //groupInventory.setStocks(stocks);

    //groupInventoryReposity.save(groupInventory);

    Product product =  productRepository.findByNo(productNo).orElse(null);
    product.setStocks(stocks);
    productRepository.save(product);
    }
}
