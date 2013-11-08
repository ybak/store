package models.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
import util.GsonExclude;

@Entity
@Table(name = "store_medium")
public class ProductMedium extends Model {

    public static enum MediaType {
        IMAGE, VIDEO
    }

    public String mediaURL;

    public MediaType mediaType; // 0: image , 1: video

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
        pm.mediaType = MediaType.IMAGE;
        pm.product = product;
        pm.save();
        product.media.add(pm);
        return pm;
    }

}
