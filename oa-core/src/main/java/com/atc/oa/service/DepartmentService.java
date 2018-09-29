package com.atc.oa.service;

import com.atc.oa.entity.Department;
import com.atc.oa.pojo.DepartmentQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * DepartmentService - 部门业务层接口
 *
 * @author Lijin
 * @version 1.0.0
 */
public interface DepartmentService extends BaseService<Department, Long> {

    Department getByName(String depName);

    List<Department> getByParentId(Long parentId);

    Page<Department> getDepartmentByDepType(Pageable pageable, DepartmentQuery departmentQuery);

    List<Department> findChildrenIds(Long parentId);
}
