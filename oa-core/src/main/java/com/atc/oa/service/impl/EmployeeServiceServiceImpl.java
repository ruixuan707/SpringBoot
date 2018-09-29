package com.atc.oa.service.impl;

import com.atc.oa.dao.EmployeeDao;
import com.atc.oa.entity.Department;
import com.atc.oa.entity.Employee;
import com.atc.oa.pojo.EmployeeQuery;
import com.atc.oa.service.DepartmentService;
import com.atc.oa.service.EmployeeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceImpl - 用户业务层
 *
 * @author Lijin
 * @version 1.0.0
 */
@Service
public class EmployeeServiceServiceImpl extends BaseServiceImpl<Employee, Long> implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Page<Employee> getList(Pageable pageable, EmployeeQuery employeeQuery) {
        Page<Employee> result = employeeDao.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                Predicate[] predicates = new Predicate[predicateList.size()];
                query.where(predicateList.toArray(predicates));
                return query.getRestriction();
            }
        },pageable);
        List<Employee> employeeList = result.getContent();
        if(CollectionUtils.isNotEmpty(employeeList)){
            for (Employee employee:employeeList){
                Department department = employee.getDepartment();
                if(department.getDepType() == 2){
                    Department businessParent = departmentService.find(department.getParentId());
                    department.setBusinessName(businessParent.getName());
                    department.setDepName(department.getName());
                }else if(department.getDepType() == 3){
                    Department departmentParent = departmentService.find(department.getParentId());
                    department.setDepName(departmentParent.getName());
                    Department businessParent = departmentService.find(departmentParent.getParentId());
                    department.setBusinessName(businessParent.getName());
                    department.setOrganizationName(department.getName());
                }else if(department.getDepType() == 4){
                    Department organizationParent = departmentService.find(department.getParentId());
                    department.setOrganizationName(organizationParent.getName());
                    Department departmentParent = departmentService.find(organizationParent.getParentId());
                    department.setDepName(departmentParent.getName());
                    Department businessParent = departmentService.find(departmentParent.getParentId());
                    department.setBusinessName(businessParent.getName());
                    department.setGroupName(department.getName());
                }else {
                    department.setBusinessName(department.getName());
                }
            }
        }
        return result;
    }

    @Override
    public int findByDepartmentId(List<String> depIds) {
        if(CollectionUtils.isNotEmpty(depIds)){
            String string = String.join(",",depIds);
            return employeeDao.findByDepartmentId(string);
        }else {
            return 0;
        }
    }

    @Override
    public List<Employee> login(String name, String password) {
        List<Employee> employeeList = employeeDao.findByNameAndPassword(name,password);
        return employeeList;
    }
}