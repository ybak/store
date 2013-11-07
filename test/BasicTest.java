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
    }

}
