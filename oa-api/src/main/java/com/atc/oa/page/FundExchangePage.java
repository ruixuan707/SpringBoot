package com.atc.oa.page;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * FundExchangePage
 *
 * @author Monco
 * @version 1.0.0
 */
@Data
public class FundExchangePage extends BasePage {

    private static final long serialVersionUID = 7397903276757705095L;
    /** id */
    private Long id;
    /** 流水号 */
    private String exchangeNo;
    /** 订单号 */
    private String orderNo;
    /** 出款卡号 */
    private String outCardNo;
    /** 出款账户 */
    private String outAccount;
    /** 收款卡号 */
    private String inCardNo;
    /** 收款账户 */
    private String inAccount;
    /** 机器码 */
    private String deviceId;
    /** 手机号码 */
    private String phoneNo;
    /** 交易金额 */
    private BigDecimal exchangeMoney;
    /** 转账状态 */
    private Integer exchangeStatus;
    /** 状态 */
    private Integer dataStatus;
    /** 备注 */
    private String remarks;
    /** 出款时间 */
    private Date outDate;
    /** 交易截图 */
    private String pic;
    /** 登录密码 */
    private String loginPwd;
    /** 取款密码 */
    private String exchangePwd;
    /** 错误原因 */
    private String errorReason;
}