package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Collection extends Model {

    @Required
    public String name;

    @ManyToMany
    @JoinTable(name = "collection_product", joinColumns = { @JoinColumn(name = "collection_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
    public List<Product> products;

}