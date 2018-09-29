package com.atc.oa.service.impl;

import com.atc.oa.dao.DepartmentDao;
import com.atc.oa.entity.Department;
import com.atc.oa.pojo.DepartmentQuery;
import com.atc.oa.service.DepartmentService;
import com.atc.oa.service.EmployeeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * DepartmentServiceImpl - 部门业务层
 *
 * @author Mengke
 * @version 1.0.0
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Long> implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeService employeeService;

    @Override
    @Transactional(readOnly = true)
    public Department getByName(String depName) {
        return departmentDao.findByDepNameLike(depName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getByParentId(Long parentId) {
        return departmentDao.findByParentId(parentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Department> getDepartmentByDepType(Pageable pageable, DepartmentQuery departmentQuery) {
        Page<Department> result = departmentDao.findAll(new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                if(departmentQuery.getDepType()!=null){
                    predicateList.add(criteriaBuilder.equal(
                            root.get("depType").as(Integer.class),
                            departmentQuery.getDepType()));
                }
                if(!StringUtils.isBlank(departmentQuery.getName())){
                    predicateList.add(criteriaBuilder.equal(
                            root.get("name").as(String.class),
                            departmentQuery.getName()));
                }
                if(departmentQuery.getBusinessId()!=null){
                    predicateList.add(criteriaBuilder.equal(
                            root.get("businessId").as(String.class),
                            departmentQuery.getBusinessId()));
                }
                if(departmentQuery.getDepId()!=null){
                    predicateList.add(criteriaBuilder.equal(
                            root.get("depId").as(String.class),
                            departmentQuery.getDepId()));
                }
                if(departmentQuery.getOrganizationId()!=null){
                    predicateList.add(criteriaBuilder.equal(
                            root.get("organizationId").as(String.class),
                            departmentQuery.getOrganizationId()));
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                query.where(predicateList.toArray(predicates));
                return query.getRestriction();
            }
        },pageable);
        //对result再次改造（将其他三个字段加进去，加入在线员工字段）
        if(result !=null && CollectionUtils.isNotEmpty(result.getContent())){
            List<Department> departmentList = result.getContent();
            if(CollectionUtils.isNotEmpty(departmentList)){
                for (Department department:departmentList) {
                    int count = 0;
                    if(department.getDepType() == 2){
                        Department businessParent = this.find(department.getParentId());
                        department.setBusinessName(businessParent.getName());
                    }else if(department.getDepType() == 3){
                        Department departmentParent = this.find(department.getParentId());
                        department.setDepName(departmentParent.getName());
                        Department businessParent = this.find(departmentParent.getParentId());
                        department.setBusinessName(businessParent.getName());
                    }else if(department.getDepType() == 4){
                        Department organizationParent = this.find(department.getParentId());
                        department.setOrganizationName(organizationParent.getName());
                        Department departmentParent = this.find(organizationParent.getParentId());
                        department.setDepName(departmentParent.getName());
                        Department businessParent = this.find(departmentParent.getParentId());
                        department.setBusinessName(businessParent.getName());
                    }else {

                    }
                    List<String> ids = new ArrayList<>();
                    for (Department department1:this.findChildrenIds(department.getId())){
                        ids.add(department1.getId()+"");
                    }
                    this.findChildrenIds(department.getId());
                    count = employeeService.findByDepartmentId(ids);
                    department.setCount(count);
                }
            }
        }
        return result;
    }

    @Override
    public List<Department> findChildrenIds(Long id) {
        List<Department> departmentList = new ArrayList<>();
        getAllDepartment(id,departmentList);
        departmentList.add(this.find(id));
        return departmentList;
    }

    public void getAllDepartment(Long id,List<Department> departments){
        List<Department> departmentList = departmentDao.findByParentId(id);
        if(CollectionUtils.isNotEmpty(departmentList)){
            for (Department department:departmentList){
                departments.add(department);
                getAllDepartment(department.getId(),departments);
            }
        }
    }
}