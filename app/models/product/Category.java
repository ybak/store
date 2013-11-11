package models.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.Store;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name = "store_category")
public class Category extends Model {

    @ManyToOne
    public Store store;

    @Required
    public String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<CategoryProduct> products = new ArrayList<CategoryProduct>();

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
        Iterator<CategoryProduct> iterator = products.iterator();
        while (iterator.hasNext()) {
            CategoryProduct cp = iterator.next();
            if (cp.product.id.equals(product.id)) {
                iterator.remove();
            }
        }
        this.save();
        return this;
    }

    public List<CategoryProduct> getEnabledProducts() {
        List<CategoryProduct> cps = new ArrayList<CategoryProduct>();
        for (CategoryProduct cp : products) {
            if (cp.enabled) {
                cps.add(cp);
            }
        }
        return cps;
    }
}