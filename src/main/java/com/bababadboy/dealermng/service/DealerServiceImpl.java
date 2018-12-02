package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.pojo.Address;
import com.bababadboy.dealermng.pojo.Gender;
import com.bababadboy.dealermng.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Ash
 */
@Service
public class DealerServiceImpl implements DealerService {
    private final DealerRepository dealerRepository;

    @Autowired
    public DealerServiceImpl(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    @Override
    public Page<Dealer> listDealersByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return dealerRepository.findAll(pageable);
    }
}
