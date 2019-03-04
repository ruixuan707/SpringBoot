package com.atc.oa.service;

import com.atc.oa.entity.FundExchange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * FundExchangeService
 *
 * @author Monco
 * @version 1.0.0
 */
public interface FundExchangeService extends BaseService<FundExchange, Long> {

    Page<FundExchange> getFundExchange(Pageable pageable, FundExchange fundExchange);

    FundExchange getFundExchange(String orderNo);
}
