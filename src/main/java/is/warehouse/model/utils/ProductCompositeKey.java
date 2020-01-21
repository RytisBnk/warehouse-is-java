package is.warehouse.model.utils;

import java.time.LocalDate;
import java.util.Objects;

public class ProductCompositeKey {
    private String name;
    private long code;
    private LocalDate expiration;

    public ProductCompositeKey(String name, long code, LocalDate expiration) {
        this.name = name;
        this.code = code;
        this.expiration = expiration;
    }

    public String getName() {
        return name;
    }

    public long getCode() {
        return code;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCompositeKey that = (ProductCompositeKey) o;
        return code == that.code &&
                Objects.equals(name, that.name) &&
                Objects.equals(expiration, that.expiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, expiration);
    }
}
