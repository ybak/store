package models.cart;

import play.db.jpa.Model;
import models.member.Member;
import models.product.Product;

public class CartItem extends Model {
    public Integer quantity;// 数量
    public Product product;// 商品
    public Member member;// 会员
}
