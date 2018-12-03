package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ash
 */
@RestController
public class DealerController {
    /*
    @Autowired
    private DealerService dealerService;*/

    @Autowired
    private DealerRepository dealerRepository;

    @RequestMapping(value = "/dealer", method = RequestMethod.GET)
    public Object listDealer() {
        List<Dealer> dealers = dealerRepository.findAll();
        Object json = JSON.toJSON(dealers);
        return json;
    }

}
