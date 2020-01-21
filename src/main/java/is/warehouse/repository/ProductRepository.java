package is.warehouse.repository;

import is.warehouse.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findByExpiration(LocalDate date);
    List<Product> findByQuantity(int minQuantity);
}
