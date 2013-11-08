package models.order;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * 实体类 - 订单日志
 */

//@Entity
public class OrderLog extends Model {

    // 订单日志类型（订单创建、订单修改、订单支付、订单退款、订单发货、订单退货、订单完成、订单作废）
    public static enum OrderLogType {
        CREATE, MODIFY, PAYMENT, REFUND, SHIPPING, RESHIP, COMPLETED, INVALID
    };

    public OrderLogType orderLogType;// 订单日志类型
    public String orderSn;// 订单编号
    public String operator;// 操作员
    public String info;// 日志信息

    public Order order;// 订单

}