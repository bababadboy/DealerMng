package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ash
 * @date 2018/12/22 19:47
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据username查找User
     * @param username
     * @return user
     */
    User findByUsername(String username);

    /**
     * 根绝username判定是否存在User
     * @param username
     * @return boolean
     */
    boolean existsByUsername(String username);

    boolean existsUserByUsername(String username);
}
