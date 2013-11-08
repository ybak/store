package models.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
import util.GsonExclude;

@Entity
@Table(name = "store_product_item")
public class ProductItem extends Model {

    public boolean enabled;

    public int inventory;

    @ManyToOne
    @GsonExclude
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
