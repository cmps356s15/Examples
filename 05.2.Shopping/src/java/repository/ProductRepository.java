package repository;

import entity.Product;
import entity.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Singleton;

@Singleton
public class ProductRepository {

    List<ProductCategory> productsCategory;
    List<Product> products;

    public ProductRepository() {
        init();
    }

    public Product getProduct(int productId) {
        return products.stream().filter(p -> p.getId() == productId).findFirst().get();
    }
    
    public List<Product> getProducts(String category) {
        if (category.equalsIgnoreCase("all")) {
            return products;
        } else {
            return products.stream().filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());
        }
    }

    public List<ProductCategory> getProductCategory() {
        return productsCategory;
    }

    private void init() {
        products = new ArrayList<>();
        products.add(new Product(1, "Apple", 7, "fruit"));
        products.add(new Product(2, "Banana", 4, "fruit"));
        products.add(new Product(3, "Grapes", 12, "fruit"));
        products.add(new Product(4, "Potato", 3, "vegetable"));
        products.add(new Product(5, "Carrot", 5, "vegetable"));
        products.add(new Product(6, "Lettuce", 4, "vegetable"));

        productsCategory = new ArrayList<>();
        productsCategory.add(new ProductCategory("all", "All"));
        productsCategory.add(new ProductCategory("fruit", "Fruits"));
        productsCategory.add(new ProductCategory("vegetable", "Vegetables"));
    }
}
