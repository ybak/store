package models.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Category extends Model {

    @Required
    public String name;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "category_product", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
    public List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category addProduct(Product product) {
        this.products.add(product);
        this.save();
        return this;
    }
}