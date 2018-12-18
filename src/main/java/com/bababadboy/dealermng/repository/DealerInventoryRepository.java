package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.DealerInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DealerInventoryRepository extends JpaRepository<DealerInventory,Long> {
    @Override
    List<DealerInventory> findAll();


    @Override
    Page<DealerInventory> findAll(Pageable pageable);

    @Override
    <S extends DealerInventory> S save(S entity);
}

