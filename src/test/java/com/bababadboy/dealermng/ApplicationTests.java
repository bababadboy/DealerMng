package com.bababadboy.dealermng;

import com.bababadboy.dealermng.utils.SpringContextAssisor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("test","value");
        Assert.assertEquals("value",stringRedisTemplate.opsForValue().get("test"));
    }

    @Test
    public void getBean(){
        Object o = SpringContextAssisor.getBeanDefinition("userDetails");
        Assert.assertNotNull(o);
    }
}
