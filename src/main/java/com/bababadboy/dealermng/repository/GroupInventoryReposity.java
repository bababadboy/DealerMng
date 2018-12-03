package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.GroupInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupInventoryReposity extends JpaRepository<GroupInventory,Long> {
    @Override
    List<GroupInventory> findAll();
}
