package com.atc.oa.service.impl;

import com.atc.oa.dao.RoleDao;
import com.atc.oa.entity.Role;
import com.atc.oa.pojo.RoleQuery;
import com.atc.oa.service.RoleService;
import org.apache.commons.lang3.StringUtils;
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
 * RoleServiceImpl - 角色业务层
 *
 * @author Lijin
 * @version 1.0.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> getRoleList(Pageable pageable, RoleQuery roleQuery) {
        Page<Role> result = roleDao.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(!StringUtils.isBlank(roleQuery.getRoleName())){
                    predicateList.add(criteriaBuilder.equal(
                            root.get("roleName").as(String.class),
                            roleQuery.getRoleName()));
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                query.where(predicateList.toArray(predicates));
                return query.getRestriction();
            }
        },pageable);
        return result;
    }

    @Override
    public List<Role> getRole() {
        return roleDao.getRoleList();
    }
}