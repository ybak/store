package models.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;
import util.GsonExclude;

@Entity
public class CategoryProduct extends Model {

    @ManyToOne
    @GsonExclude
    public Category category;

    @ManyToOne
    public Product product;

    public boolean enabled;

    public CategoryProduct() {
    }

    public CategoryProduct(Category category, Product product, boolean enabled) {
        this.category = category;
        this.product = product;
        this.enabled = enabled;
    }

}