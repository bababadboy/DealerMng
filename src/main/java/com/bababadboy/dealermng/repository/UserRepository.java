package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author wangxiaobin
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    boolean existsUserByUsername(String username);

    @Override
    <S extends User> S save(S entity);

    @Transactional
    void deleteUserByUsername(String username);
}
