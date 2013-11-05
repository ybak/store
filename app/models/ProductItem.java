package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class ProductItem extends Model  {
    
    @ManyToOne
    public Product product;
    
    @ManyToOne
    public ProductOptionSelection firstOptionItem;

    @ManyToOne
    public ProductOptionSelection secondOptionItem;
    
    public boolean enabled;
    
    public int inventory;
    
    public float totalPrice;
}
