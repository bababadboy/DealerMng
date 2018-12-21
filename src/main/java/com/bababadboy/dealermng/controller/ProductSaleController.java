package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.entity.ProductSaleInfo;
import com.bababadboy.dealermng.repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.spring.web.json.Json;

import java.net.URI;
import java.util.*;

/**
 * Product Sale Info Controller
 *
 * @author TwinkleN1
 */

@RestController
public class ProductSaleController {
    @Autowired
    private ProductSaleRepository productSaleRepository;

    @GetMapping(value="/productSales")
    public Object all()
    {
       // System.out.println("get all");
        List<ProductSaleInfo> psList = productSaleRepository.findAll();
       // System.out.println(JSON.toJSON(psList));
        return psList;
    }

    @GetMapping(value = "/productSales/{id}")
    public Object getSaleInfoById(@PathVariable("id") Long id){
        //System.out.println("get id");
        ProductSaleInfo psi= productSaleRepository.findProductSaleInfoById(id);
       // ProductSaleInfo psi= productSaleRepository.findById(id);
        return psi;
    }

    @RequestMapping(value = "/productSales/{id}",method = RequestMethod.DELETE)
    public void deleteSaleInfo(@PathVariable("id") Long id){
        productSaleRepository.deleteById(id);
    }

    @RequestMapping(value = "/productSales",method = RequestMethod.POST)
    public ResponseEntity<ProductSaleInfo> createSaleInfo(@RequestBody ProductSaleInfo psiBody){
        ProductSaleInfo savePS = productSaleRepository.save(psiBody);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savePS.getId()).toUri();
    return ResponseEntity.created(location).build();
    }


    @PutMapping("/productSales/{id}")
    public ResponseEntity<Object> updateProductSaleInfo(@RequestBody ProductSaleInfo productSaleInfo,@PathVariable Long id)
    {
        ProductSaleInfo psi = productSaleRepository.findProductSaleInfoById(id);
        productSaleRepository.save(productSaleInfo);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "productSale/quantity")
    public Object saleQuantity(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year,0,1);
        List<ProductSaleInfo> listOfYear = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);
        JSONObject jsonObject = new JSONObject( );
        int quantity=0;
        int days = 0;
        for(ProductSaleInfo psi:listOfYear)
        {
            days++;
            quantity+=psi.getProductNums();
        }

        jsonObject.put("totalYear",quantity);
        jsonObject.put("averagePerDay",quantity/days);
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-10);
        quantity = 0;
        List<ProductSaleInfo> listOfTenDays = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);
        JSONArray jsonArray = new JSONArray();

        int quantityA = 0;
        int quantityB=0;
        for(int i =0; i<10;i++)
        {
            for(ProductSaleInfo psi:listOfTenDays)
                quantityA += psi.getProductNums();
            calendar.add(Calendar.DATE,+1);
            listOfTenDays = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);

            for(ProductSaleInfo psi:listOfTenDays)
                quantityB+=psi.getProductNums();

        jsonArray.add(i,quantityA-quantityB);
        }

        jsonObject.put("recent",jsonArray);
    return jsonObject;
    }

    @GetMapping(value = "/productSale/amount")
    public Object saleAmount()
    {
        int year,month,date,minute,second,week,dayOfWeek;
        Calendar today = Calendar.getInstance();
        Calendar thisYear = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        Calendar lastWeek = Calendar.getInstance();
        Calendar twoWeeksAgo = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        today.setTime(new Date());
        year = today.get(Calendar.YEAR);
        month =today.get(Calendar.MONTH);
        date = today.get(Calendar.DATE);
        second =today.get(Calendar.SECOND);
        dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        week = today.get(Calendar.WEEK_OF_MONTH);
        today.set(year,month,date);
        yesterday.set(year,month,date);
        yesterday.add(Calendar.DATE,-1);
        thisYear.set(year,-1,1);

        //今年
        List<ProductSaleInfo> listOfYear = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(thisYear);
        int totalAmount=0;
        for(ProductSaleInfo psi:listOfYear)
        {
            totalAmount +=psi.getOrderAmount().intValue();
        }
        //【今天】
        List<ProductSaleInfo> listOfDay = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(today);

        int todayAmount=0;
        for(ProductSaleInfo psi:listOfDay)
        {
            todayAmount +=psi.getOrderAmount().intValue();
        }
        //今天【昨天】
        List <ProductSaleInfo> listOfTwoDays = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(yesterday);
        int lastAmount=0;
        for(ProductSaleInfo psi:listOfTwoDays)
        {
            lastAmount +=psi.getOrderAmount().intValue();
        }

        //今天昨天【前天】
        calendar.set(year,month,date);
        calendar.add(Calendar.DATE,-2);
        List <ProductSaleInfo> listOfThreeDays = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);

        int twoDatsAmount=0;
        for(ProductSaleInfo psi:listOfThreeDays)
        {
            twoDatsAmount +=psi.getOrderAmount().intValue();
        }

        //上周
        calendar.set(year,month,date);
        calendar.add(Calendar.DATE,-dayOfWeek-7);
        List <ProductSaleInfo> listOfLastWeek = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);

        int lastWeekAmount=0;
        for(ProductSaleInfo psi:listOfLastWeek)
        {
            lastWeekAmount +=psi.getOrderAmount().intValue();
        }


        //上上周
        calendar.set(year,month,date);
        calendar.add(Calendar.DATE,-dayOfWeek-7*2);
        List <ProductSaleInfo> listOfTwoWeeksAgo = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);

        int twoWeekAmount=0;
        for(ProductSaleInfo psi:listOfTwoWeeksAgo)
        {
            twoWeekAmount +=psi.getOrderAmount().intValue();
        }



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalYear",totalAmount );
        jsonObject.put("perDay", lastAmount);
        jsonObject.put("compareWeek",lastWeekAmount- Math.abs(lastWeekAmount-twoWeekAmount) );
        jsonObject.put("compareDay", lastAmount-Math.abs(twoDatsAmount-lastAmount));

        return jsonObject;
    }

    @GetMapping(value = "/productSale/amount/category")
    public Object amountCategory(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year,-1,1  );
        List<ProductSaleInfo> listOfyear = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);

        Map<String,Integer> map = new HashMap<>();
         for(ProductSaleInfo psi:listOfyear  )
        {
            int amount = 0;
            String str = psi.getProduct().getCategories();
            int ifExist =1;
            if(!map.isEmpty()){
                for(Map.Entry<String,Integer> entry: map.entrySet()){
                    if(str.equals(entry.getKey())) {
                        amount = entry.getValue() + psi.getOrderAmount().intValue();
                        entry.setValue(amount);

                        System.out.println("amount is :"+amount+"   entry value is:"+entry.getValue());
                    }
                   else ifExist++;
                    System.out.println("MapSize is :"+map.size());
                    System.out.println("ifExist is :"+ifExist);
                }
                if(ifExist>map.size())//new cateroy
                {
                    map.put(str,psi.getOrderAmount().intValue());
                }

            }
            else {

                map.put(str,psi.getOrderAmount().intValue());
            }


        }
        int index = map.size();
        List<JSONObject > jsonList = new ArrayList<>();
        index = 0;
        for(Map.Entry<String,Integer> entry : map.entrySet())
        {
            JSONObject jsonObject = new JSONObject( );
            System.out.println("category:"+entry.getKey()+" amount :"+entry.getValue());
            jsonObject.put("category",entry.getKey());
            jsonObject.put("amount",entry.getValue());
            jsonList.add(jsonObject );
        }
        return jsonList;
    }
}
