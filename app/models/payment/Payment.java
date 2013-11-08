package models.payment;

import java.math.BigDecimal;

import models.order.Order;

public class Payment {
    // 支付类型（在线充值、预存款支付、在线支付、线下支付）
    public enum PaymentType {
        RECHARGE, DEPOSIT, ONLINE, OFFLINE
    };

    // 支付状态（准备、超时、作废、成功、失败）
    public enum PaymentStatus {
        READY, TIMEOUT, INVALID, SUCCESS, FAILURE
    };

    public String paymentSn;// 支付编号
    public PaymentType paymentType;// 支付类型
    public String paymentConfigName;// 支付配置名称
    public String bankName;// 收款银行名称
    public String bankAccount;// 收款银行账号
    public BigDecimal totalAmount;// 支付金额
    public BigDecimal paymentFee;// 支付手续费
    public String payer;// 付款人
    public String operator;// 支付宝交易号
    public String memo;// 备注
    public PaymentStatus paymentStatus;// 支付状态

    public PaymentConfig paymentConfig;// 支付配置
    public Order order;// 订单

}
