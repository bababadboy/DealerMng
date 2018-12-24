package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.GroupInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupInventoryReposity extends JpaRepository<GroupInventory,Long> {
    @Override
    List<GroupInventory> findAll();

    @Override
    <S extends GroupInventory> S save(S entity);
    GroupInventory findGroupInventoryByGroupWarehouseAndProduct(Long warehouseId,Long productId);
    @Override
    Page<GroupInventory> findAll(Pageable pageable);

    @Override
    boolean existsById(Long aLong);
}
