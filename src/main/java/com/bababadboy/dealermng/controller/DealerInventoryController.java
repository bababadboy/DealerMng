package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.DealerInventory;
import com.bababadboy.dealermng.repository.DealerInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author iYmz
 */
@Transactional
@RestController
public class DealerInventoryController {
    @Autowired
    private DealerInventoryRepository dealerInventoryRepository;

    @GetMapping(value = "/dealerinventories")
    public List<DealerInventory> retrieveAllInventory(){
        List<DealerInventory> list = dealerInventoryRepository.findAll();

        return list;
    }
}
