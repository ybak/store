package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Category extends Model {

    @Required
    public String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "category_product", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
    public List<Product> products;

}