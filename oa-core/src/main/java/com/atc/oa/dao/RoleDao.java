package com.atc.oa.dao;

import com.atc.oa.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleDao - 角色持久层
 *
 * @author Lijin
 * @version 1.0.0
 */
@Repository
public interface RoleDao extends BaseDao<Role, Long> {

    @Query(value = "select * from fmgt_role", nativeQuery = true)
    List<Role> getRoleList();
}

