package is.warehouse.repository.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import is.warehouse.configuration.ApplicationProperties;
import is.warehouse.model.Product;
import is.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CSVProductRepository implements ProductRepository {
    private List<Product> products;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public CSVProductRepository(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.products = readProductsFromCsv();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public List<Product> findByExpiration(LocalDate date) {
        return products.stream()
                .filter(product -> product.getExpiration().isBefore(date) || product.getExpiration().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByQuantity(int minQuantity) {
        return products.stream()
                .filter(product -> product.getQuantity() <= minQuantity)
                .collect(Collectors.toList());
    }

    private List<Product> readProductsFromCsv() {
        List<Product> csvData = new LinkedList<>();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(applicationProperties.getFilepath()));
        } catch (IOException e) {
            System.out.println("CSV file not found, check file path");
            System.exit(-1);
        }
        try {
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Product.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Product product : csvToBean) {
                csvData.add(product);
            }
        }
        catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
            System.exit(-1);
        }
        return csvData;
    }
}
