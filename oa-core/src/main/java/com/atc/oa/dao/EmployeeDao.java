package com.atc.oa.dao;

import com.atc.oa.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao - 用户持久层
 *
 * @author Lijin
 * @version 1.0.0
 */
@Repository
public interface EmployeeDao extends BaseDao<Employee, Long> {
    @Query(value = "select count(*) from fmgt_employee u where u.department_id in (?1)", nativeQuery = true)
    int findByDepartmentId(String depIds);

    List<Employee> findByNameAndPassword(String name,String password);
}

