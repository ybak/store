package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class ProductItem extends Model  {
    
    @ManyToOne
    public Product product;
    
    public boolean enabled;
    
    public int inventory;
    
    public float totalPrice;
    
    @OneToMany
    public List<ProductOptionSelection> optionSelectionList;
}
