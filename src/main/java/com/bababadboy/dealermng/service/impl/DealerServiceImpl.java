package com.bababadboy.dealermng.service.impl;

import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ash
 */
@Service
public class DealerServiceImpl implements DealerService {
    @Autowired
    private DealerRepository dealerRepository;


}
