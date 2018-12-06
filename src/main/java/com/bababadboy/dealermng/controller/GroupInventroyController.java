package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.GroupInventory;
import com.bababadboy.dealermng.repository.GroupInventoryReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
public class GroupInventroyController {
    @Autowired
    private GroupInventoryReposity groupInventoryReposity;

    @GetMapping(value = "/groupInventory")
    public List<GroupInventory>  retrieveAllInventory(){
        List<GroupInventory> list = groupInventoryReposity.findAll();

        return list;
    }
    


}
