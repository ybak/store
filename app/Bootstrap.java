import play.*;
import play.jobs.*;
import play.test.*;
import models.*;
import models.product.Category;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        // Check if the database is empty
        if (Category.count() == 0) {
            Fixtures.loadModels("initial-data.yml");
        }
    }

}