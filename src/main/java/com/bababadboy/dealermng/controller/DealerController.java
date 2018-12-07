package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Ash
 */
@RestController
@RequestMapping(value = "/dealers")
public class DealerController {
    private final DealerService dealerService;

    private final DealerRepository dealerRepository;

    @Autowired
    public DealerController(DealerService dealerService, DealerRepository dealerRepository) {
        this.dealerService = dealerService;
        this.dealerRepository = dealerRepository;
    }

    /**
     * 显示所有经销商
     * @param page 页码
     * @return 分页后的经销商json数据
     */
    @RequestMapping(method = RequestMethod.GET)
    public Object listDealers(@RequestParam("page") Integer page) {
        Page<Dealer> dealers = dealerService.listDealersByPage(page, 10);
        return JSON.toJSON(dealers.getContent());
    }

    /**
     * 显示指定经销商信息
     * @param id 经销商id
     * @return 指定经销商json数据
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findDealer(@PathVariable("id") Long id) {
        Optional<Dealer> dealer = dealerRepository.findById(id);
        if (!dealer.isPresent()) {
            return new ResponseEntity<>("dealer not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(JSON.toJSON(dealer.get()), HttpStatus.OK);
    }

    /**
     * 新增经销商
     * @param dealer 经销商详情
     * @return 操作结果
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createDealer(@RequestBody Dealer dealer) {
        // TODO dealer parameter check
        Date date = new Date();
        dealer.setRegisterAt(date);
        dealerRepository.save(dealer);
        return new ResponseEntity<>("dealer created", HttpStatus.CREATED);
    }

    /**
     * 删除经销商
     * @param id 经销商id
     * @return 操作结果
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDealer(@PathVariable("id") Long id) {
        Optional<Dealer> toBeDeleteDealer = dealerRepository.findById(id);
        if (!toBeDeleteDealer.isPresent()) {
            return new ResponseEntity<>("dealer not found", HttpStatus.BAD_REQUEST);
        }
        dealerRepository.deleteById(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

    /**
     * 更新经销商所有信息
     * @param id 经销商id
     * @param dealer 更新后的经销商详情
     * @return 操作结果
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> putDealer(@PathVariable("id") Long id, @RequestBody Dealer dealer) {
        Optional<Dealer> toBePutDealer = dealerRepository.findById(id);
        if (!toBePutDealer.isPresent()) {
            return new ResponseEntity<>("dealer not found", HttpStatus.BAD_REQUEST);
        }
        dealer.setId(id);
        dealerRepository.save(dealer);
        return new ResponseEntity<>("dealer updated", HttpStatus.OK);
    }

    /**
     * 选择性更新经销商信息
     * @param id 经销商id
     * @param map 经销商部分数据信息
     * @return 操作结果
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> patchDealer(@PathVariable("id") Long id,
                                         @RequestBody Map<String, Object> map) {
        // TODO map data check
        Optional<Dealer> toBePatchedDealer = dealerRepository.findById(id);
        if (toBePatchedDealer.isPresent()) {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(toBePatchedDealer.get());
            for (String key : map.keySet()) {
                jsonObject.put(key, map.get(key));
            }
            dealerRepository.save(JSON.toJavaObject(jsonObject, Dealer.class));
        }
        return new ResponseEntity<>("dealer updated", HttpStatus.OK);
    }
}
