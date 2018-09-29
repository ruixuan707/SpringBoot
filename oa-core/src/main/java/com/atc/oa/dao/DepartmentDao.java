package com.atc.oa.dao;

import com.atc.oa.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DepartmentDao - 部门持久层
 *
 * @author Lijin
 * @version 1.0.0
 */
@Repository
public interface DepartmentDao extends BaseDao<Department, Long> {

    Department findByDepNameLike(String depName);

    List<Department> findByParentId(Long parentId);
}
