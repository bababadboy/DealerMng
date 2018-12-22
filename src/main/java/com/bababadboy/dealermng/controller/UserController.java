package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bababadboy.dealermng.dto.UserDataDTO;
import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.service.impl.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxiaobin
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signup")
    public Object signUp(@RequestBody User user) {
        JSONObject json = new JSONObject();
        json.put("access_token", userService.signUp(user));
        return JSON.toJSON(json);
    }

    @PostMapping("/login")
    public Object logIn(@RequestParam String username,@RequestParam String password){
        String token = userService.logIn(username,password);
        UserDataDTO info = modelMapper.map(userService.search(username),UserDataDTO.class);
        JSONObject result = new JSONObject();
        result.put("userMsg",info);
        result.put("accessToken",token);
        return JSON.toJSON(result);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // 只有admin才能有删除权限
    @DeleteMapping(value = "/{username}")
    public String deleteUser(@PathVariable String username){
         userService.delete(username);
         return username;
    }

    /**
     * 查找用户
     */
    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "The user doesn't exist"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Object search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username),UserDataDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDataDTO whoami(HttpServletRequest req) {
         return modelMapper.map(userService.whoami(req),UserDataDTO.class);
    }

}
