package com.atc.oa.service;

import com.atc.oa.entity.Role;
import com.atc.oa.pojo.RoleQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * RoleService - 角色业务层接口
 *
 * @author Lijin
 * @version 1.0.0
 */
public interface RoleService extends BaseService<Role, Long> {

    Page<Role> getRoleList(Pageable pageable, RoleQuery roleQuery);

    List<Role> getRole();
}