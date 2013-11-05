package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class ProductInventory extends Model  {
    
    @ManyToOne
    public Product product;
    
    @ManyToOne
    public ProductOptionItem firstOptionItem;

    @ManyToOne
    public ProductOptionItem secondOptionItem;
    
    public boolean active;
    
    public int inventory;
}
