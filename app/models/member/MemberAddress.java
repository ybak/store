package models.member;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "store_member_address")
public class MemberAddress extends Model {

    public String name;// 收货人姓名
    public String areaPath;// 收货地区路径
    public String address;// 地址
    public String phone;// 电话
    public String mobile;// 手机
    public String zipCode;// 邮编
    public boolean isDefault;// 是否默认

    @ManyToOne
    public Member member;// 会员

}
