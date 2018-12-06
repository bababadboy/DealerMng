package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.BusinessException;
import com.bababadboy.dealermng.entity.Product;

/**
 * @author wangxiaobin
 * @description:
 * @create 2018-12-03 下午4:11
 */
public class ProductRepoCustomImpl implements ProductRepoCustom{

    @Override
    public void method() {
        System.out.println("Who are you !!");
        throw new BusinessException("Err here");
    }
}
