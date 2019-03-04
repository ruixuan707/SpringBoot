package com.atc.oa.service.impl;

import com.atc.oa.dao.FundExchangeDao;
import com.atc.oa.entity.FundExchange;
import com.atc.oa.service.FundExchangeService;
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
 * FundExchangeServiceImpl
 *
 * @author Monco
 * @version 1.0.0
 */
@Service
public class FundExchangeServiceImpl extends BaseServiceImpl<FundExchange, Long> implements FundExchangeService {

    @Autowired
    private FundExchangeDao fundExchangeDao;

    @Override
    public Page<FundExchange> getFundExchange(Pageable pageable, FundExchange fundExchange) {
        Page<FundExchange> result = fundExchangeDao.findAll(new Specification<FundExchange>() {
            @Override
            public Predicate toPredicate(Root<FundExchange> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                Predicate[] predicates = new Predicate[predicateList.size()];
                criteriaQuery.where(predicateList.toArray(predicates));
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        return result;
    }

    @Override
    public FundExchange getFundExchange(String orderNo) {
        return fundExchangeDao.getFundExchange(orderNo);
    }
}