package is.warehouse.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import is.warehouse.model.utils.LocalDateConverter;
import is.warehouse.model.utils.ProductCompositeKey;

import java.time.LocalDate;

public class Product {
    @CsvBindByName(column = "Item name")
    private String name;
    @CsvBindByName(column = "Code")
    private long code;
    @CsvBindByName(column = "Quantity")
    private int quantity;
    @CsvCustomBindByName(column = "Expiration date", converter = LocalDateConverter.class)
    private LocalDate expiration;

    public Product() {
    }

    public Product(ProductCompositeKey key , int quantity) {
        this.name = key.getName();
        this.code = key.getCode();
        this.expiration = key.getExpiration();
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public ProductCompositeKey getProductCompositeKey() {
        return new ProductCompositeKey(name, code, expiration);
    }
}
