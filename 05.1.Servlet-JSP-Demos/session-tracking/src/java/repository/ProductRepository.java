package repository;

import entity.Product;
import entity.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class ProductRepository {
    public List<Product> getProducts(String category) {
        List<Product> products = new ArrayList<Product>();
        
        if (category.equals("fruit")) {
            products.add(new Product(1, "Apple", 7));
            products.add(new Product(2, "Banana", 4));
            products.add(new Product(3, "Grapes", 12));
        } else {
            products.add(new Product(1, "Potato", 3));
            products.add(new Product(2, "Carrot", 5));
            products.add(new Product(3, "Onion", 4));
        }
        return products;
    }
    
    public List<ProductCategory> getProductCategory() {
        List<ProductCategory> productsCategory = new ArrayList<ProductCategory>();
        productsCategory.add(new ProductCategory("fruit", "Fruits"));
        productsCategory.add(new ProductCategory("vegetable", "Vegetables"));
        
        return productsCategory;
    }
}
