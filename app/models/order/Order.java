package models.order;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;

import models.member.Member;
import play.db.jpa.Model;

//@Entity
public class Order extends Model {

    // 订单状态（未处理、已处理、已完成、已作废）
    public static enum OrderStatus {
        UNPROCESSED, PROCESSED, COMPLETED, INVALID
    };

    // 付款状态（未支付、已支付、全额退款）
    public static enum PaymentStatus {
        UNPAID, PAID, REFUNDED
    };

    // 配送状态（未发货、已发货、已退货）
    public static enum ShippingStatus {
        UNSHIPPED, SHIPPED, RESHIPED
    };

    public String orderSn;// 订单编号
    public OrderStatus orderStatus;// 订单状态
    public PaymentStatus paymentStatus;// 支付状态
    public ShippingStatus shippingStatus;// 发货状态
    public BigDecimal productTotalPrice;// 商品总价格
    public BigDecimal deliveryFee;// 配送费用
    public BigDecimal paymentFee;// 支付费用
    public BigDecimal totalAmount;// 订单总额
    public BigDecimal paidAmount;// 已付金额
    public Double productWeight;// 商品重量
    public Integer productTotalQuantity;// 商品总数
    public String shipName;// 收货人姓名
    public String shipArea;// 收货地区
    public String shipAreaPath;// 收货地区路径
    public String shipAddress;// 收货地址
    public String shipZipCode;// 收货邮编
    public String shipPhone;// 收货电话
    public String shipMobile;// 收货手机
    public String memo;// 附言

    public Member member;// 会员
    public Set<OrderItem> orderItemSet;// 订单项
    public Set<OrderLog> orderLogSet;// 订单日志

    // public WeightUnit productWeightUnit;// 商品重量单位
    // public DeliveryType deliveryType;// 配送方式
    // public PaymentConfig paymentConfig;// 支付方式
    // public String deliveryTypeName;// 配送方式名称
    // public String paymentConfigName;// 支付方式名称

    // public Set<Payment> paymentSet;// 收款
    // public Set<Refund> refundSet;// 退款
    // public Set<Shipping> shippingSet;// 发货
    // public Set<Reship> reshipSet;// 退货

}