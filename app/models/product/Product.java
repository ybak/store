package models.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class Product extends Model {

    public String title;

    public float price;

    public float retailPrice;

    public boolean enabled;

    public String overview;

    public String details;

    public Date lastUpdatedAt;// 乐观锁

    public boolean isInventoryManaged;

    @ManyToMany(mappedBy = "products")
    public List<Category> categoriesList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<ProductItem> productItemsList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<ProductMedia> mediaList = new ArrayList<>();

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

    public Product addItem(boolean enabled, int inventory) {
        ProductItem item = new ProductItem(this, enabled, inventory).save();
        this.productItemsList.add(item);
        this.save();
        return this;
    }

    public Product addMedia(String picture) {
        ProductMedia media = ProductMedia.buildPicture(this, picture).save();
        this.mediaList.add(media);
        this.save();
        return this;
    }

}