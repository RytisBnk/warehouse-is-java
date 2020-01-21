package is.warehouse.service.impl;

import is.warehouse.model.Product;
import is.warehouse.model.utils.ProductCompositeKey;
import is.warehouse.repository.ProductRepository;
import is.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return mergeProducts(productRepository.findAll());
    }

    @Override
    public List<Product> getProductsByExpiration(LocalDate expirationDate) {
        return mergeProducts(productRepository.findByExpiration(expirationDate));
    }

    @Override
    public List<Product> getProductsByQuantity(int maxQuantity) {
        return mergeProducts(productRepository.findByQuantity(maxQuantity));
    }

    private List<Product> mergeProducts(List<Product> products) {
        Map<ProductCompositeKey, Integer> mergedProducts =  products.stream().collect(
                Collectors.groupingBy(Product::getProductCompositeKey,
                        Collectors.summingInt(Product::getQuantity))
        );
        List<Product> mergedProductList = new LinkedList<>();
        mergedProducts.forEach((key, value) -> mergedProductList.add(new Product(key, value)));
        mergedProductList.sort(Comparator.comparing(Product::getName));
        return mergedProductList;
    }
}
