package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.DealerInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerInventoryRepository extends JpaRepository<DealerInventory,Long> {
    @Override
    List<DealerInventory> findAll();
}
