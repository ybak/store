package models.member;

import java.util.Set;

import models.product.Product;
import play.db.jpa.Model;

public class Member extends Model {
    public String username;// 用户名
    public String password;// 密码
    public String email;// E-mail

    public Boolean isAccountLocked;// 账号是否锁定

    public Set<Receiver> receiverSet;// 收货地址
    public Set<Product> favoriteProductSet;// 收藏夹商品

}
