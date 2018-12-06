package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ash
 */
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    @Override
    List<Dealer> findAll();

    @Override
    Page<Dealer> findAll(Pageable pageable);

    @Override
    <S extends Dealer> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
