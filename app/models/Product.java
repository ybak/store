package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Product extends Model {

    public String title;

    public float weight;
    
    public String details;
    
    public String ribbon;
    
    public float price;
    
    public float retailPrice;
    
    public boolean isInventoryManaged;
    
    public String overview;
    
    public boolean enabled;
    
    public Date lastUpdatedAt;//乐观锁
    
    public Product() {
    }

    public Product(String title, float price, String overview, String details, float retailPrice,
            float weight) {
        this.title = title;
        this.price = price;
        this.overview = overview;
        this.details = details;
        this.retailPrice = retailPrice;
        this.weight = weight;
    }

    @ManyToMany(mappedBy = "products")
    public List<Category> categoriesList;

    @OneToMany(mappedBy = "product")
    public List<ProductOption> optionsList;

    @OneToMany(mappedBy = "product")
    public List<ProductItem> productsItemsList;

    @OneToMany(mappedBy = "product")
    public List<ProductMedia> mediaList;
    
}