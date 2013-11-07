package models.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class ProductMedia extends Model {

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
    public Product product;

    public static ProductMedia buildPicture(Product product, String picture) {
        ProductMedia pm = new ProductMedia();
        pm.mediaURL = picture;
        pm.mediaType = 0;
        pm.product = product;
        pm.save();
        product.mediaList.add(pm);
        return pm;
    }

}
