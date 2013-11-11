package models.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import models.Store;
import models.product.Product;
import models.product.ProductItem;
import play.db.jpa.Model;

@Entity
@Table(name = "store_order_item")
public class OrderItem extends Model {

    public String productName;// 商品名称
    public BigDecimal productPrice;// 商品价格
    public BigDecimal orginalPrice;// 商品原始价格
    public Integer productQuantity;// 商品数量
    public String promotionDesc;

    @ManyToOne
    public Store store;
    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order order;// 订单
    @ManyToOne
    public ProductItem productItem;// 商品

}