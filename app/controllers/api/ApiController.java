package controllers.api;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import models.product.Category;
import play.mvc.Controller;
import util.GsonUtil;

public class ApiController extends Controller {

    public static void getCategoryProducts() throws Exception {
        List<Category> categories = Category.findAll();
        renderJSON(GsonUtil.toJson(categories));
    }

    public static void addProduct() throws Exception {
    }

    public static void updateProduct() throws Exception {
    }

    public static void getStoreOrders() throws Exception {
    }

    public static void getMemberOrders() throws Exception {
    }

    public static void udpateOrder() throws Exception {
    }

    public static void memberConfirmOrder() throws Exception {
    }
    
}