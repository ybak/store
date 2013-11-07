import org.junit.*;

import java.util.*;

import javax.persistence.EntityTransaction;

import play.db.jpa.JPA;
import play.db.jpa.JPABase;
import play.db.jpa.Transactional;
import play.test.*;
import models.*;
import models.product.Category;
import models.product.Product;
import models.product.ProductMedia;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void testAddProducts() {
        Category category = new Category("Clothing").save();
        Product product1 = new Product(true, true, "漂亮裙子", 300f, 500f, "很漂亮的裙子", "非常漂亮的裙子,很合适").save();
        product1.addItem(true, 30).addMedia("/picture/skirt.jpg").addMedia("/picture/skirt2.jpg");
        category.addProduct(product1);

        Product product2 = new Product(true, true, "漂亮T恤", 200f, 400f, "很漂亮的T恤", "非常漂亮的T恤,很合适").save();
        product2.addItem(true, 20).addMedia("/picture/tshirt.jpg");
        category.addProduct(product2);

        List<Product> products = Product.findAll();
        Assert.assertEquals(2, products.size());

        Category first = Category.find("name", "Clothing").first();
        System.out.println(first);
    }

    @Test
    public void fullTest() {
        Fixtures.loadModels("data.yml");
        Category category = Category.find("name", "Electronics").first();
        
        Product iPad = new Product(true, true, "iPad", 150f, 250f, "iPad is a line of tablet computers designed and marketed by Apple Inc.", "iPad is a line of tablet computers designed and marketed by Apple Inc., which runs Apple's iOS. The first iPad was released on April 3, 2010; the most recent iPad models, the iPad Air and second generation iPad Mini, were revealed on October 22, 2013 and will go on sale November 1, 2013 and late November respectfully. The user interface is built around the device's multi-touch screen, including a virtual keyboard. The iPad has built-in Wi-Fi and, on some models, cellular connectivity.").save();
        iPad.addItem(true, 20).addMedia("http://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/IPad_Air.png/220px-IPad_Air.png");
        category.addProduct(iPad);
    }

}
