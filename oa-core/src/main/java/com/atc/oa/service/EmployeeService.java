package com.atc.oa.service;

import com.atc.oa.entity.Employee;
import com.atc.oa.pojo.EmployeeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * UserService - 用户业务层接口
 *
 * @author Lijin
 * @version 1.0.0
 */
public interface EmployeeService extends BaseService<Employee, Long> {

    Page<Employee> getList(Pageable pageable, EmployeeQuery employeeQuery);

    int findByDepartmentId(List<String> depIds);

    List<Employee> login(String name,String password);
}