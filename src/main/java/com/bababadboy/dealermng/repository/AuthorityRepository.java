package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ash
 * @date 2018/12/22 22:36
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
