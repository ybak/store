package models.payment;

import java.math.BigDecimal;
import java.util.Set;

import models.order.Order;

public class PaymentConfig {
    // 支付配置类型（支付宝）
    public enum PaymentConfigType {
        ALIPAY
    };

    public PaymentConfigType paymentConfigType;// 支付配置类型
    public String name;// 支付方式名称
    public BigDecimal paymentFee;// 支付费用
    public String description;// 介绍
    public Integer orderList;// 排序
    public String configObjectStore;// 配置对象信息储存

    public Set<Order> orderSet;// 订单
    public Set<Payment> paymentSet;// 支付
}
