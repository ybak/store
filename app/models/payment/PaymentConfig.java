package models.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;
import models.Store;
import models.order.Order;

@Entity
@Table(name = "store_payment_config")
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
    public List<Order> orders;// 订单
    @OneToMany(mappedBy = "paymentConfig", cascade = CascadeType.ALL)
    public List<Payment> payments;// 支付
    
    @ManyToOne
    public Store store;
}
