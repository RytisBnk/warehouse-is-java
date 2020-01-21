package is.warehouse.service;

import is.warehouse.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByExpiration(LocalDate expirationDate);
    List<Product> getProductsByQuantity(int maxQuantity);
}
