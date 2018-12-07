package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.DealerWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerWarehouseReposity extends JpaRepository<DealerWarehouse,Long> {

    @Override
    List<DealerWarehouse> findAll();
}
