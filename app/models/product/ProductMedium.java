package models.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
import util.GsonExclude;

@Entity
public class ProductMedium extends Model {

    public String mediaURL;

    public int mediaType; // 0: picture , 1: video

    // 属性未启用
    // public String mediaIconURL;
    //
    // public int mediaHeight;
    //
    // public int mediaWidth;
    //
    // public int mediaTitle;

    @ManyToOne
    @GsonExclude
    public Product product;

    public static ProductMedium buildPicture(Product product, String picture) {
        ProductMedium pm = new ProductMedium();
        pm.mediaURL = picture;
        pm.mediaType = 0;
        pm.product = product;
        pm.save();
        product.media.add(pm);
        return pm;
    }

}
