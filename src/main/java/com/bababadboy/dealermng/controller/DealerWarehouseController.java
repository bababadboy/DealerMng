package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.DealerInventory;
import com.bababadboy.dealermng.entity.DealerWarehouse;
import com.bababadboy.dealermng.repository.DealerWarehouseReposity;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DealerWarehouseController {

    @Autowired
    private DealerWarehouseReposity dealerWarehouseReposity;

    @GetMapping("/dealerWarehouses")
    public List<DealerWarehouse> retrieveAllInventory(){
        List<DealerWarehouse> list = dealerWarehouseReposity.findAll();

        return list;
    }

    @GetMapping(value = "/dealerWarehousePage")
    public Page<DealerWarehouse> retrievePagesInventory(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DealerWarehouse> p = dealerWarehouseReposity.findAll(pageable);
        return p;
    }
}
