import org.junit.*;

import java.util.*;

import play.test.*;
import models.*;
import models.Category;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }
    
    @Test
    public void testTags() {
        Category collection = new Category("Clothing").save();
        
//        Product product = new Product("漂亮裙子", 300f, "/image/skirt01.jpg", "很漂亮的裙子", "非常漂亮的裙子,很合适", 500f, 0).save();
    }

}
