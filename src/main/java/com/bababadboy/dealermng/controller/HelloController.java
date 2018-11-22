package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.bababadboy.dealermng.entity.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 测试Controller
 */


@RestController
public class HelloController {
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
//        String[] strings = new String[3];
//        strings[0] = "demo1";
//        strings[1] = "demo2";
//        strings[2] = "demo3";
//        String json = JSON.toJSONString(strings);
//        return json;
        return "demo";
    }
}