package com.atc.oa.service.impl;

import com.atc.oa.dao.TemplateDao;
import com.atc.oa.entity.Template;
import com.atc.oa.pojo.TemplateQuery;
import com.atc.oa.service.TemplateService;
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
 * TemplateServiceImpl
 *
 * @author Lijin
 * @version 1.0.0
 */
@Service
public class TemplateServiceImpl extends BaseServiceImpl<Template, Long> implements TemplateService {

    @Autowired
    private TemplateDao templateDao;

    @Override
    public Page<Template> getRoleList(Pageable pageable, TemplateQuery templateQuery) {
        Page<Template> result = templateDao.findAll(new Specification<Template>() {
            @Override
            public Predicate toPredicate(Root<Template> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(StringUtils.isBlank(templateQuery.getTemplateName())){
                    predicateList.add(criteriaBuilder.equal(
                            root.get("templateName").as(String.class),
                            templateQuery.getTemplateName()));
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                query.where(predicateList.toArray(predicates));
                return query.getRestriction();
            }
        },pageable);
        return result;
    }

    @Override
    public List<Template> getTemplate() {
        return null;
    }
}