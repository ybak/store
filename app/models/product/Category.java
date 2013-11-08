package models.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Category extends Model {

    @Required
    public String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<CategoryProduct> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category addProduct(Product product) {
        CategoryProduct cp = new CategoryProduct(this, product, true);
        this.products.add(cp);
        product.categories.add(cp);
        this.save();
        return this;
    }

    public Category deleteProduct(Product product) {
        this.products.remove(product);
        this.save();
        return this;
    }
}