package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 测试Controller
 * @author Ash
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String demo() {
        return "demo";
    }
}