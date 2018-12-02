package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.entity.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

/**
 * @author Ash
 */
public interface DealerService {

    /**
     * 分页获取经销商信息
     * @param page 页码
     * @param size 每页数据大小
     * @return 分页集合
     */
    Page<Dealer> listDealersByPage(Integer page, Integer size);
}
