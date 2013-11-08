package models.member;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import models.cart.CartItem;
import models.product.Product;
import play.db.jpa.Model;

@Entity
public class Member extends Model {
    public String username;// 用户名
    public String password;// 密码
    public String email;// E-mail

    public Boolean isAccountLocked;// 账号是否锁定

    @OneToMany(mappedBy = "member")
    public List<Receiver> receivers;// 收货地址
    @OneToMany(mappedBy = "member")
    public List<CartItem> cartItems;// 购物车项
    @ManyToMany
    public Set<Product> favoriteProducts;// 收藏夹商品

}
