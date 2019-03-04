package com.atc.oa.api;

import com.atc.oa.entity.FundExchange;
import com.atc.oa.page.ApiResult;
import com.atc.oa.page.FundExchangePage;
import com.atc.oa.page.PageResult;
import com.atc.oa.pojo.OrderQuery;
import com.atc.oa.service.FundExchangeService;
import com.atc.oa.util.HttpRequestUtil;
import com.atc.oa.util.RandomUtils;
import com.atc.oa.util.UpdateTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * FundExchangeController
 *
 * @author Monco
 * @version 1.0.0
 */
@RestController
@RequestMapping("/fund-exchange")
public class FundExchangeController {

    @Autowired
    private FundExchangeService fundExchangeService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody FundExchange fundExchange) {
        fundExchange.setExchangeNo(RandomUtils.RandomCode());
        fundExchange.setDataStatus(0);
        fundExchange.setExchangeStatus(0);
        fundExchangeService.save(fundExchange);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody FundExchangePage fundExchangePage) {
        FundExchange source = fundExchangeService.find(fundExchangePage.getId());
        FundExchange entity = new FundExchange();
        BeanUtils.copyProperties(fundExchangePage, entity);
        UpdateTool.copyNullProperties(entity, source);
        fundExchangeService.save(source);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<PageResult> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "20") Integer size,
                                           FundExchange fundExchange, OrderQuery orderQuery) {
        Page<FundExchange> pageList = fundExchangeService.getFundExchange(OrderQuery.getQuery(orderQuery, page, size), fundExchange);
        List<FundExchange> fundExchangeList = pageList.getContent();
        List<FundExchangePage> fundExchangePageList = new ArrayList<>();
        for (FundExchange exchange : fundExchangeList) {
            FundExchangePage fundExchangePage = new FundExchangePage();
            BeanUtils.copyProperties(exchange, fundExchangePage);
            fundExchangePage.setId(exchange.getId());
            fundExchangePageList.add(fundExchangePage);
        }
        PageResult pageResult = new PageResult(pageList.getPageable(), fundExchangePageList, pageList.getTotalElements());
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("{id}")
    public ResponseEntity<FundExchangePage> list(@PathVariable Long id) {
        FundExchangePage fundExchangePage = new FundExchangePage();
        FundExchange fundExchange = fundExchangeService.find(id);
        BeanUtils.copyProperties(fundExchange, fundExchangePage);
        return ResponseEntity.ok(fundExchangePage);
    }

    @GetMapping("out")
    public ResponseEntity<String> getOut(@RequestParam Long id) {
        FundExchange fundExchange = fundExchangeService.find(id);
        String url = "http://118.24.93.206:22221/api/transfer/ICBCTransfer";
        String recAcct = fundExchange.getInCardNo();
        String recName = fundExchange.getInAccount();
        String pwd = fundExchange.getExchangePwd();
        BigDecimal money = fundExchange.getExchangeMoney();
        String deviceId = "123456789";
        String para = "recAcct=" + recAcct + "&recName=" + recName + "&pwd=" + pwd + "&money=" + money.toPlainString() + "&deviceId=" + deviceId + "orderNo" + fundExchange.getOrderNo();
        String result = HttpRequestUtil.sendPost(url, para, false);
        return ResponseEntity.ok(result);
    }

    @GetMapping("setting")
    public ResponseEntity<String> getSetting(@RequestParam Long id) {
        FundExchange fundExchange = fundExchangeService.find(id);
        String url = "http://118.24.93.206:22221/api/transfer/ICBCSetAccount";
        String para = "recAcct=" + fundExchange.getPhoneNo() + "&recPwd=" + fundExchange.getLoginPwd() + "&deviceId=" + fundExchange.getDeviceId();
        String result = HttpRequestUtil.sendPost(url, para, false);
        return ResponseEntity.ok(result);
    }

    @GetMapping("callback")
    public ResponseEntity<ApiResult> callback(@RequestParam String orderNo) {
        ApiResult apiResult = new ApiResult();
        if (StringUtils.isBlank(orderNo)) {
            apiResult.setStatus(500);
            apiResult.setMsg("传入的订单号为空！");
            return ResponseEntity.ok(apiResult);
        }
        FundExchange fundExchange = fundExchangeService.getFundExchange(orderNo);
        if (fundExchange == null) {
            apiResult.setStatus(500);
            apiResult.setMsg("查不到此订单号！");
            return ResponseEntity.ok(apiResult);
        } else {
            apiResult.setStatus(200);
            apiResult.setMsg("成功！");
            return ResponseEntity.ok(apiResult);
        }
    }
}