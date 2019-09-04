package com.bababadboy.dealermng;

import com.bababadboy.dealermng.controller.ProductController;
import com.bababadboy.dealermng.repository.ProductRepository;
import com.bababadboy.dealermng.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author wangxiaobin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductControllerTest {

    private MockMvc mvc;
    private ProductRepository productRepository;
    private ProductServiceImpl productService;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new ProductController(productRepository, productService)).build();
    }

    @Test
    public void testProductController() throws Exception{
        // 1.查询列表
        mvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk());// 判断响应状态是否成功
    }
}
