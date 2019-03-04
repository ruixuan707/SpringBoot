package com.atc.oa.dao;

import com.atc.oa.entity.FundExchange;
import org.springframework.data.jpa.repository.Query;

/**
 * FundExchangeDao
 *
 * @author Monco
 * @version 1.0.0
 */
public interface FundExchangeDao extends BaseDao<FundExchange, Long> {

    @Query(value = "select * from fund_exchange where order_no = ?1", nativeQuery = true)
    FundExchange getFundExchange(String orderNo);
}
