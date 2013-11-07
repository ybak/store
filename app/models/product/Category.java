package models.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Use Set here for many-to-many performance reason.
    // http://stackoverflow.com/questions/8174667/hibernate-many-to-many-relations-set-or-list
    @ManyToMany
    @JoinTable(name = "category_product", joinColumns = { @JoinColumn(name = "category") }, inverseJoinColumns = { @JoinColumn(name = "product") })
    public Set<Product> products = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }

    public Category addProduct(Product product) {
        this.products.add(product);
        product.categories.add(this);
        this.save();
        return this;
    }

    public Category deleteProduct(Product product) {
        this.products.remove(product);
        this.save();
        return this;
    }
}