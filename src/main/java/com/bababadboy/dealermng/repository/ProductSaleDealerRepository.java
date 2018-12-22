package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Dealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductSaleDealerRepository extends JpaRepository<Dealer,Long> {

    Dealer findDealerById(Long aLong);
    @Override
    Optional<Dealer> findById(Long aLong);
}
