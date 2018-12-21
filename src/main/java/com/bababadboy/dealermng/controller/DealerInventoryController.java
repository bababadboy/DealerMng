package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.entity.DealerInventory;
import com.bababadboy.dealermng.repository.DealerInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

/**
 * @author iYmz
 */
@Transactional
@RestController
public class DealerInventoryController {
    @Autowired
    private DealerInventoryRepository dealerInventoryRepository;

    @GetMapping(value = "/dealerInventory")
    public List<DealerInventory> retrieveAllInventory() {
        List<DealerInventory> list = dealerInventoryRepository.findAll();

        return list;
    }

    @GetMapping(value = "/dealerInventoryPage")
    public Page<DealerInventory> retrievePagesInventory(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DealerInventory> p = dealerInventoryRepository.findAll(pageable);
        return p;
    }


    @RequestMapping(value = "/dealerInventory", method = RequestMethod.POST)
    public ResponseEntity<DealerInventory> createDealerInventory(@RequestBody DealerInventory dibody) {
        DealerInventory di = dealerInventoryRepository.save(dibody);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(di.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
