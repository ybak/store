package controllers.api;

import java.util.List;

import models.product.Category;
import play.mvc.Controller;
import util.GsonUtil;

public class ApiController extends Controller {

    public static void getCategoryProducts() throws Exception {
        List<Category> categories = Category.findAll();
        renderJSON(GsonUtil.toJson(categories));
    }

}