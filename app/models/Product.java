package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Product extends Model {

    @Required
    public String title;

    @Required
    public float price;

    public String images;

    public String overview;

    public String detail;

    public float retailPrice;

    public float weight;

    public int totalInventory;

    @ManyToMany(mappedBy = "products")
    public List<Collection> collections;

    @OneToMany(mappedBy = "product")
    public List<ProductOption> productOptions;

    @OneToMany(mappedBy = "product")
    public List<ProductInventory> inventories;

}