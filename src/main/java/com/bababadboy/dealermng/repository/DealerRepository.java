package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ash
 */
@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

}
