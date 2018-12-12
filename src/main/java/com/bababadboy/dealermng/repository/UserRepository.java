package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaobin
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    @Override
    <S extends User> S save(S entity);
}
