package models.cart;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;
import models.member.Member;
import models.product.Product;

@Entity
@Table(name = "store_cart_item")
public class CartItem extends Model {
    public Integer quantity;// 数量
    @ManyToOne
    public Product product;// 商品
    @ManyToOne
    public Member member;// 会员
}
