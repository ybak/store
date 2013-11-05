package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class ProductOptionSelection extends Model  {

    @ManyToOne
    @Required
    public ProductOption productOption;
    
    public String value;
    
    public String description;
    
    public float priceChange;
}
