package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaobin
 */
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {

    ApplicationUser findByUsername(String username);
}
