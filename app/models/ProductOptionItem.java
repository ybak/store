package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class ProductOptionItem extends Model  {

    @ManyToOne
    @Required
    public ProductOption productOption;
    
    public String name;
    
    public String description;
    
    public float surcharge;
}
