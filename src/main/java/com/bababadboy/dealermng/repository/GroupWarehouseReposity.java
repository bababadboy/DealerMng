package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.GroupWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupWarehouseReposity extends JpaRepository<GroupWarehouse,Long> {
    @Override
    List<GroupWarehouse> findAll();

    @Override
    GroupWarehouse getOne(Long aLong);
}
