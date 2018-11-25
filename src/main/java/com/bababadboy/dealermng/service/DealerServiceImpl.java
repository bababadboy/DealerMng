package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ash
 */
public class DealerServiceImpl implements DealerService {
    @Autowired
    private DealerRepository dealerRepository;


}
