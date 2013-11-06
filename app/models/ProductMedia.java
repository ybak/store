package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class ProductMedia extends Model {

    public String mediaURL;

    public int mediaType;

    public String mediaIconURL;

    public int mediaHeight;

    public int mediaWidth;

    public int mediaTitle;

}
