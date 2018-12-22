package com.bababadboy.dealermng.repository;

import com.bababadboy.dealermng.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaobin
 */
public interface GroupRepository extends JpaRepository<Group,Long> {

    boolean existsGroupByGroupName(String username);
    Group findByGroupName(String username);

    @Override
    <S extends Group> S save(S entity);
}
