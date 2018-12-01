package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.DealerWarehouse;
import com.bababadboy.dealermng.repository.DealerWarehouseReposity;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DealerWarehouseController {

    @Autowired
    private DealerWarehouseReposity dealerWarehouseReposity;

    @GetMapping("/dealerwarehouses")
    public List<DealerWarehouse> retrieveAllInventory(){
        List<DealerWarehouse> list = dealerWarehouseReposity.findAll();

        return list;
    }

}
