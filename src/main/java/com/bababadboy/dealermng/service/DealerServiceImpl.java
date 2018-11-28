package com.bababadboy.dealermng.service;

import com.bababadboy.dealermng.repository.DealerRepository;
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
