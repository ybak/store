package models.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class Product extends Model {

    public String title;

    public float price;

    public float retailPrice;

    public boolean enabled;

    public String overview;

    @Lob
    @MaxSize(10000)
    public String details;

    public Date lastUpdatedAt;// 乐观锁

    public boolean isInventoryManaged;

    @ManyToMany(mappedBy = "products")
    public Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<ProductItem> productItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<ProductMedium> media = new ArrayList<>();

    // ************* 未启用属性 **************//
    // public float weight;
    //
    // public String ribbon;
    //
    // @OneToMany(mappedBy = "product")
    // public List<ProductOption> optionsList;

    public Product() {
    }

    public Product(boolean enabled, boolean isInventoryManaged, String title, float price, float retailPrice,
            String overview, String details) {
        this.title = title;
        this.price = price;
        this.retailPrice = retailPrice;
        this.enabled = enabled;
        this.overview = overview;
        this.details = details;
        this.lastUpdatedAt = new Date();
    }

    public Product delete() {
        for (Category category : categories) {
            category.deleteProduct(this);
        }
        return this;
    }

    public Product addItem(boolean enabled, int inventory) {
        ProductItem item = new ProductItem(this, enabled, inventory).save();
        this.productItems.add(item);
        this.save();
        return this;
    }

    public Product addMedia(String picture) {
        ProductMedium media = ProductMedium.buildPicture(this, picture).save();
        this.media.add(media);
        this.save();
        return this;
    }

}