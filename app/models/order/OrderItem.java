package models.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import models.product.Product;
import play.db.jpa.Model;

/**
 * Bean类 - 订单项
 */

//@Entity
public class OrderItem extends Model {

    public String productSn;// 商品货号
    public String productName;// 商品名称
    public BigDecimal productPrice;// 商品价格

    public String productHtmlFilePath;// 商品HTML静态文件路径
    public Integer productQuantity;// 商品数量
    public Integer deliveryQuantity;// 发货数量
    public Integer totalDeliveryQuantity;// 总发货量

    public Order order;// 订单
    public Product product;// 商品

}