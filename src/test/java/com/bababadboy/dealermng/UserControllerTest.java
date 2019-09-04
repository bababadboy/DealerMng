package com.bababadboy.dealermng;

import com.bababadboy.dealermng.controller.ProductController;
import com.bababadboy.dealermng.controller.UserController;
import com.bababadboy.dealermng.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author wangxiaobin
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    private  UserService userService;
    private  ModelMapper modelMapper;
    private MockMvc mvc;
    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new UserController(userService,modelMapper)).build();
    }

    @Test
    public void testUserController() throws Exception{
        // 1.查询列表
        mvc.perform(MockMvcRequestBuilders.get("/signup"))
                .andExpect(MockMvcResultMatchers.status().isOk());// 判断响应状态是否成功
    }

}
