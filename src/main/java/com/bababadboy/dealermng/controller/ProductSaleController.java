package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bababadboy.dealermng.entity.Product;
import com.bababadboy.dealermng.entity.ProductSaleInfo;
import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.repository.ProductRepository;
import com.bababadboy.dealermng.repository.ProductSaleDealerRepository;
import com.bababadboy.dealermng.repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.spring.web.json.Json;
import com.bababadboy.dealermng.entity.Dealer;

import javax.transaction.Transactional;
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
    private DealerRepository dealerRepository;
    @Autowired
    private ProductSaleDealerRepository productSaleDealerRepository;
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

    @GetMapping(value = "/productSale/rank/dealer")
        public Object saleDealer(){//经销商购买额排名
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        List<ProductSaleInfo> listOfYear;
        List<ProductSaleInfo> listOfMonth;
        //find list of year by month;
        double[] amountOfYear = new double[month+1];
        double[] amountOfMonth = new double[day];
        JSONArray jsonArrayYear = new JSONArray( );
        JSONArray jsonArrayMonth = new JSONArray();
        begin.set(year,0,1);
        end.set(year,month,day);
        listOfYear  = productSaleRepository.findProductSaleInfosByPaymentTimeBetween(begin,end);

        Map<Long,Integer> map = new HashMap<>();
        for(ProductSaleInfo psi:listOfYear  )
        {
            int amount = 0;
            Long dealerId = psi.getDealer().getId();
            int ifExist =1;
            if(!map.isEmpty()){
                for(Map.Entry<Long,Integer> entry: map.entrySet()){
                    if(dealerId==entry.getKey()) {
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
                    map.put(dealerId,psi.getOrderAmount().intValue());
                }

            }
            else {

                map.put(dealerId,psi.getOrderAmount().intValue());
            }


        }


        for(int i  = 0;i <map.size();i++) {
            int value = 0;
            Long id = 0L;
            for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                    if(value>=entry.getValue());
                    else {
                        value = entry.getValue();
                        id = entry.getKey();
                        System.out.println("id = "+id);
                        entry.setValue(0);

                        JSONObject jsonObject = new JSONObject();
                       // Dealer dealer = productSaleDealerRepository.getOne(id);

                        //Dealer dealer = productSaleDealerRepository.findOne(id);
                        Dealer dealer = productSaleDealerRepository.findDealerById(id);
                        jsonObject.put("name",dealer.getName());
                        jsonObject.put("amount",value);
                        jsonArrayYear.add(jsonObject);
                    }
            }

            if(i == 5)break;
        }

        //Dealer 月排名 取前六位
        begin.set(year,month,1);
        end.set(year,month,day);
        listOfMonth = productSaleRepository.findProductSaleInfosByPaymentTimeBetween(begin,end);

        Map<Long,Integer> mapOfMonth = new HashMap<>();
        for(ProductSaleInfo psi:listOfMonth  )
        {
            int amount = 0;
            Long dealerId = psi.getDealer().getId();
            int ifExist =1;
            if(!mapOfMonth.isEmpty()){
                for(Map.Entry<Long,Integer> entry: mapOfMonth.entrySet()){
                    if(dealerId==entry.getKey()) {
                        amount = entry.getValue() + psi.getOrderAmount().intValue();
                        entry.setValue(amount);

                        System.out.println("amount is :"+amount+"   entry value is:"+entry.getValue());
                    }
                    else ifExist++;
                    System.out.println("MapSize is :"+mapOfMonth.size());
                    System.out.println("ifExist is :"+ifExist);
                }
                if(ifExist>mapOfMonth.size())//new cateroy
                {
                    mapOfMonth.put(dealerId,psi.getOrderAmount().intValue());
                }

            }
            else {

                mapOfMonth.put(dealerId,psi.getOrderAmount().intValue());
            }


        }

        for(int i  = 0;i <mapOfMonth.size();i++) {
            int value = 0;
            Long id = 0L;
            for (Map.Entry<Long, Integer> entry : mapOfMonth.entrySet()) {
                if(value>=entry.getValue());
                else {
                    value = entry.getValue();
                    id = entry.getKey();
                    entry.setValue(0);

                    JSONObject jsonObject = new JSONObject();
                   // Dealer dealer = productSaleDealerRepository.getOne(id);
                    Dealer dealer = productSaleDealerRepository.findDealerById(id);
                    jsonObject.put("name",dealer.getName());
                    jsonObject.put("amount",value);
                    jsonArrayMonth.add(jsonObject);
                }
            }

            if(i == 5)break;
        }






        JSONObject jsonObjectReturn  = new JSONObject();
        jsonObjectReturn.put("year",jsonArrayYear);
        jsonObjectReturn.put("month",jsonArrayMonth);
        return jsonObjectReturn;

    }
    @Transactional
    @GetMapping(value = "productSale/amount/trend")
    public Object saleTrend(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        List<ProductSaleInfo> listOfYear;
        List<ProductSaleInfo> listOfMonth;
        //find list of year by month;
        double[] amountOfYear = new double[month+1];
        double[] amountOfMonth = new double[day];
        JSONArray jsonArrayYear = new JSONArray( );
        JSONArray jsonArrayMonth = new JSONArray();
        System.out.println("Month = "+month);
        System.out.println("day = "+day);
        for(int i = 0;i<=month;i++)
        {// JAVA Calendar Month JAN is -1

            begin.set(year,i,1);
            if (i!=month){
                end.set(year,i+1,1);
            }
            else {
                end.set(year,month,day);
            }
            listOfYear = productSaleRepository.findProductSaleInfosByPaymentTimeBetween(begin,end );
            for(ProductSaleInfo psi:listOfYear)
            {
                amountOfYear[i]+=psi.getOrderAmount().doubleValue();
            }
            jsonArrayYear.add(amountOfYear[i]);
        }


        //find list of mont by day;
        for( int i = 0;i<day;i++)
        {
            begin.set(year,month,i+1);
            if(i<day-1)end.set(year,month,i+2);
            else end.set(year,month,i+1,hour,minute);
            listOfMonth = productSaleRepository.findProductSaleInfosByPaymentTimeBetween(begin,end);

            for(ProductSaleInfo psi:listOfMonth)
            {
                amountOfMonth[i]=psi.getOrderAmount().doubleValue();
            }
            jsonArrayMonth.add(amountOfMonth[i]);
        }


        JSONObject jsonObject = new JSONObject( );
        jsonObject.put("year",jsonArrayYear);
        jsonObject.put("month",jsonArrayMonth);
        return jsonObject;
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
        if(days != 0)
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
    @GetMapping(value = "/productSales/quantity/category")
    public  Object quantityCategory(){//年销售量类别占比


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year,-1,1  );
        List<ProductSaleInfo> listOfyear = productSaleRepository.findProductSaleInfosByPaymentTimeGreaterThanEqual(calendar);

        Map<String,Integer> map = new HashMap<>();
        for(ProductSaleInfo psi:listOfyear  )
        {
            int quantity = 0;
            String str = psi.getProduct().getCategories();
            int ifExist =1;
            if(!map.isEmpty()){
                for(Map.Entry<String,Integer> entry: map.entrySet()){
                    if(str.equals(entry.getKey())) {
                        quantity = entry.getValue() + psi.getProductNums();
                        entry.setValue(quantity);

                        System.out.println("quantity is :"+quantity+"   entry value is:"+entry.getValue());
                    }
                    else ifExist++;
                    System.out.println("MapSize is :"+map.size());
                    System.out.println("ifExist is :"+ifExist);
                }
                if(ifExist>map.size())//new cateroy
                {
                    map.put(str,psi.getProductNums());
                }

            }
            else {

                map.put(str,psi.getProductNums());
            }


        }
        int index = map.size();
        List<JSONObject > jsonList = new ArrayList<>();
        index = 0;
        for(Map.Entry<String,Integer> entry : map.entrySet())
        {
            JSONObject jsonObject = new JSONObject( );
            System.out.println("category:"+entry.getKey()+" quantity :"+entry.getValue());
            jsonObject.put("category",entry.getKey());
            jsonObject.put("quantity",entry.getValue());
            jsonList.add(jsonObject );
        }
        return jsonList;

    }
    @GetMapping(value = "/productSale/amount/category")
    public Object amountCategory(){//年销售额类别占比接口
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
