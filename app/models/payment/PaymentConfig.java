package models.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
import models.order.Order;

@Entity
public class PaymentConfig extends Model {
    // 支付配置类型（支付宝）
    public enum PaymentConfigType {
        ALIPAY
    };

    public PaymentConfigType paymentConfigType;// 支付配置类型
    public String name;// 支付方式名称
    public BigDecimal paymentFee;// 支付费用
    public String description;// 介绍
    public Integer orderList;// 排序
    public String config;// 配置

    @OneToMany(mappedBy = "paymentConfig", cascade = CascadeType.ALL)
    public List<Order> orderSet;// 订单
    @OneToMany(mappedBy = "paymentConfig", cascade = CascadeType.ALL)
    public List<Payment> paymentSet;// 支付
}
