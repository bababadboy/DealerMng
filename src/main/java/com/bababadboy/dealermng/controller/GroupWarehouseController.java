package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.GroupWarehouse;
import com.bababadboy.dealermng.repository.GroupWarehouseReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupWarehouseController {

    @Autowired
    private GroupWarehouseReposity groupWarehouseReposity;

    @GetMapping("/groupwarehouses")
    public List<GroupWarehouse> retrieveAllWarehouse(){
        List<GroupWarehouse> list = groupWarehouseReposity.findAll();
        return list;
    }
}
