package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bababadboy.dealermng.entity.*;
import com.bababadboy.dealermng.repository.*;
import com.bababadboy.dealermng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.spring.web.json.Json;

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
    private ProductSaleOrderRepository productSaleOrderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductSaleDealerRepository productSaleDealerRepository;
    @GetMapping(value="/productSales")
    public Object all()
    {
       // System.out.println("get all");
        // List<ProductSaleInfo> psList = productSaleRepository.findAll();
       // System.out.println(JSON.toJSON(psList));


        List<OrderDetail> psList = productSaleOrderRepository.findAll();
        return psList;
    }

    @GetMapping(value = "/productSales/{id}")
    public Object getSaleInfoById(@PathVariable("id") Long id){
        //System.out.println("get id");
        OrderDetail psi= productSaleOrderRepository.findOrderDetailById(id);
       // ProductSaleInfo psi= productSaleRepository.findById(id);
        return psi;
    }
    /*
   // @RequestMapping(value = "/productSales/{id}",method = RequestMethod.DELETE)
  //  public void deleteSaleInfo(@PathVariable("id") Long id){
   //     productSaleRepository.deleteById(id);
   // }

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
    */

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
        List<OrderItem> listOfYear;
        List<OrderItem> listOfMonth;
        //find list of year by month;
        double[] amountOfYear = new double[month+1];
        double[] amountOfMonth = new double[day];
        JSONArray jsonArrayYear = new JSONArray( );
        JSONArray jsonArrayMonth = new JSONArray();
        begin.set(year,0,1);
        end.set(year,month,day+2);

        listOfYear  = orderItemRepository.findOrderItemsBypaidAtBetween(begin.getTime(),end.getTime());

        Map<Long,Integer> map = new HashMap<>();
        for(OrderItem psi:listOfYear  )
        {
            int amount = 0;
            Long dealerId = psi.getDealer().getId();
            int ifExist =1;
            if(!map.isEmpty()){
                for(Map.Entry<Long,Integer> entry: map.entrySet()){
                    if(dealerId==entry.getKey()) {
                        amount = entry.getValue() + psi.getOrderTotalPrice().intValue();
                        entry.setValue(amount);

                        System.out.println("amount is :"+amount+"   entry value is:"+entry.getValue());
                    }
                    else ifExist++;
                    System.out.println("MapSize is :"+map.size());
                    System.out.println("ifExist is :"+ifExist);
                }
                if(ifExist>map.size())//new cateroy
                {
                    map.put(dealerId,psi.getOrderTotalPrice().intValue());
                }

            }
            else {

                map.put(dealerId,psi.getOrderTotalPrice().intValue());
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
                         Dealer dealer = productSaleDealerRepository.findById(id).orElse(null);
                        jsonObject.put("name",dealer.getName());
                        jsonObject.put("amount",value);
                        jsonArrayYear.add(jsonObject);
                    }
            }

            if(i == 5)break;
        }

        //Dealer 月排名 取前六位
        begin.set(year,month,1);
        end.set(year,month,day+2);
        listOfMonth = orderItemRepository.findOrderItemsBypaidAtBetween(begin.getTime(),end.getTime());
        Map<Long,Integer> mapOfMonth = new HashMap<>();
        for(OrderItem psi:listOfMonth  )
        {
            int amount = 0;
            Long dealerId = psi.getDealer().getId();
            int ifExist =1;
            if(!mapOfMonth.isEmpty()){
                for(Map.Entry<Long,Integer> entry: mapOfMonth.entrySet()){
                    if(dealerId==entry.getKey()) {
                        amount = entry.getValue() + psi.getOrderTotalPrice().intValue();
                        entry.setValue(amount);

                        System.out.println("amount is :"+amount+"   entry value is:"+entry.getValue());
                    }
                    else ifExist++;
                    System.out.println("MapSize is :"+mapOfMonth.size());
                    System.out.println("ifExist is :"+ifExist);
                }
                if(ifExist>mapOfMonth.size())//new cateroy
                {
                    mapOfMonth.put(dealerId,psi.getOrderTotalPrice().intValue());
                }

            }
            else {

                mapOfMonth.put(dealerId,psi.getOrderTotalPrice().intValue());
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
                    Dealer dealer = productSaleDealerRepository.findById(id).orElse(null);

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
    @GetMapping(value = "/productSale/amount/trend")
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
        List<OrderItem> listOfYear;
        List<OrderItem> listOfMonth;
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
                end.set(year,month,day+2);
            }
            listOfYear  = orderItemRepository.findOrderItemsBypaidAtBetween(begin.getTime(),end.getTime());
            for(OrderItem psi:listOfYear)
            {
                amountOfYear[i]+=psi.getOrderTotalPrice().doubleValue();
            }
            jsonArrayYear.add(amountOfYear[i]);
        }


        //find list of mont by day;
        for( int i = 0;i<day;i++)
        {
            begin.set(year,month,i+1);
            //if(i<day-1)end.set(year,month,i+2);
            end.set(year,month,i+2);
            //else end.set(year,month,i+1,23,59);
            listOfMonth = orderItemRepository.findOrderItemsBypaidAtBetween(begin.getTime(),end.getTime());

            for(OrderItem psi:listOfMonth)
            {
                amountOfMonth[i]=psi.getOrderTotalPrice().doubleValue();
            }
            jsonArrayMonth.add(amountOfMonth[i]);
        }


        JSONObject jsonObject = new JSONObject( );
        jsonObject.put("year",jsonArrayYear);
        jsonObject.put("month",jsonArrayMonth);
        return jsonObject;
    }

    @GetMapping(value = "/productSale/quantity")
    public Object saleQuantity(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,0,1);
        List<OrderItem> listOfYear = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());
        JSONObject jsonObject = new JSONObject( );
        int quantity=0;
        for(OrderItem psi:listOfYear)
        {
            //quantity+=psi.getOrderDetails();
            List<OrderDetail> list =psi.getOrderDetails();
            for(OrderDetail od:list)
            {
                quantity+=od.getAmount();
            }
        }

        jsonObject.put("totalYear",quantity);
        //if(days != 0)
        //    jsonObject.put("averagePerDay",quantity/days);
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-10);
        quantity = 0;
        List<OrderItem> listOfTenDays =orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());
        JSONArray jsonArray = new JSONArray();

        int quantityA = 0;
        int quantityB=0;
        for(int i =0; i<10;i++)
        {
            for(OrderItem psi:listOfTenDays) {
                List<OrderDetail> list = psi.getOrderDetails();
                for (OrderDetail od : list) {
                    quantityA += od.getAmount();
                }
            }
            calendar.add(Calendar.DATE,+1);
            listOfTenDays = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());

            for(OrderItem psi:listOfTenDays)
            {
                List<OrderDetail> list = psi.getOrderDetails();
                for (OrderDetail od : list) {
                    quantityB += od.getAmount();
                }
            }


            jsonArray.add(i,quantityA-quantityB);
    if(i==9){

        jsonObject.put("averagePerDay",quantityA-quantityB);
    }
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
        date = today.get(Calendar.DAY_OF_MONTH);
        second =today.get(Calendar.SECOND);
        dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        week = today.get(Calendar.WEEK_OF_MONTH);
        today.set(year,month,date);
        yesterday.set(year,month,date);
        yesterday.add(Calendar.DATE,-1);
        thisYear.set(year,-1,1);

        //今年
        calendar.set(year,0,1);
        List<OrderItem> listOfYear = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());
        int totalAmount=0;
        for(OrderItem psi:listOfYear)
        {
            totalAmount +=psi.getOrderTotalPrice().intValue();
        }

        calendar.set(year,month,date);
        //【今天】
        List<OrderItem> listOfDay = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());

        int todayAmount=0;
        for(OrderItem psi:listOfDay)
        {
            todayAmount +=psi.getOrderTotalPrice().intValue();
        }
        calendar.add(Calendar.DATE,-1);
        //今天【昨天】
        List <OrderItem> listOfTwoDays = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());
        int lastAmount=0;
        for(OrderItem psi:listOfTwoDays)
        {
            lastAmount +=psi.getOrderTotalPrice().intValue();
        }


        //今天昨天【前天】
        calendar.set(year,month,date);
        calendar.add(Calendar.DATE,-1);
        List <OrderItem> listOfThreeDays = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());

        int twoDatsAmount=0;
        for(OrderItem psi:listOfThreeDays)
        {
            twoDatsAmount +=psi.getOrderTotalPrice().intValue();
        }


        //上周
        calendar.set(year,month,date);
        calendar.add(Calendar.DATE,-dayOfWeek-7);
        List <OrderItem> listOfLastWeek =orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());

        int lastWeekAmount=0;
        for(OrderItem psi:listOfLastWeek)
        {
            lastWeekAmount +=psi.getOrderTotalPrice().intValue();
        }


        //上上周
        calendar.set(year,month,date);
        calendar.add(Calendar.DATE,-dayOfWeek-7*2);
        List <OrderItem> listOfTwoWeeksAgo =orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());

        int twoWeekAmount=0;
        for(OrderItem psi:listOfTwoWeeksAgo)
        {
            twoWeekAmount +=psi.getOrderTotalPrice().intValue();
        }



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalYear",totalAmount );
        jsonObject.put("perDay", lastAmount);
        if(Math.abs(twoDatsAmount-lastAmount)>0)jsonObject.put("compareWeek", (lastAmount-Math.abs(twoDatsAmount-lastAmount))/Math.abs(twoDatsAmount-lastAmount));//lastWeekAmount- Math.abs(lastWeekAmount-twoWeekAmount)
       else jsonObject.put("compareWeek", null);//lastWeekAmount- Math.abs(lastWeekAmount-twoWeekAmount)

        if(Math.abs(twoDatsAmount-lastAmount)>0)jsonObject.put("compareDay", (lastAmount-Math.abs(twoDatsAmount-lastAmount))/Math.abs(twoDatsAmount-lastAmount));
        else jsonObject.put("compareDay", null );

        return jsonObject;
    }
    @GetMapping(value = "/productSales/quantity/category")
    public  Object quantityCategory(){//年销售量类别占比


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year,-1,1  );
        List<OrderItem> listOfyear = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());

        Map<String,Integer> map = new HashMap<>();
        for(OrderItem psi:listOfyear  )
        {
            int quantity = 0;
            //String str = psi.getProduct().getCategories();

            List<OrderDetail> orderDetails = psi.getOrderDetails();
            for (OrderDetail od: orderDetails){
                String str=od.getProduct().getCategories();

            int ifExist =1;
            if(!map.isEmpty()){
                for(Map.Entry<String,Integer> entry: map.entrySet()){
                    if(str.equals(entry.getKey())) {


                        quantity = entry.getValue() + od.getAmount().intValue();
                        entry.setValue(quantity);

                        System.out.println("quantity is :"+quantity+"   entry value is:"+entry.getValue());
                    }
                    else ifExist++;
                    System.out.println("MapSize is :"+map.size());
                    System.out.println("ifExist is :"+ifExist);
                }
                if(ifExist>map.size())//new cateroy
                {
                    map.put(str,od.getAmount().intValue());
                }

            }
            else {

                map.put(str,od.getAmount().intValue());
            }

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
        List<OrderItem> listOfyear = orderItemRepository.findOrderItemsBypaidAtGreaterThanEqual(calendar.getTime());

        Map<String,Integer> map = new HashMap<>();
         for(OrderItem psi:listOfyear  )
        {
            int amount = 0;
            List<OrderDetail> orderDetails = psi.getOrderDetails();

            for (OrderDetail od: orderDetails) {
                String str = od.getProduct().getCategories();

                int ifExist = 1;
                if (!map.isEmpty()) {
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (str.equals(entry.getKey())) {
                            amount = entry.getValue() + od.getTotalMoney().intValue();
                            entry.setValue(amount);

                            System.out.println("amount is :" + amount + "   entry value is:" + entry.getValue());
                        } else ifExist++;
                        System.out.println("MapSize is :" + map.size());
                        System.out.println("ifExist is :" + ifExist);
                    }
                    if (ifExist > map.size())//new cateroy
                    {
                        map.put(str, od.getTotalMoney().intValue());
                    }

                } else {

                    map.put(str, od.getTotalMoney().intValue());
                }
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
