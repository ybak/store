package models.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class ProductItem extends Model {

    public boolean enabled;

    public int inventory;

    @ManyToOne
    public Product product;

    public ProductItem(Product product, boolean enabled, int inventory) {
        super();
        this.product = product;
        this.enabled = enabled;
        this.inventory = inventory;
    }

    // 未启用属性
    // public float totalPrice;
    //
    // @OneToMany
    // public List<ProductOptionSelection> optionSelectionList;

}