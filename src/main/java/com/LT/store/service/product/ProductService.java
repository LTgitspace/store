package com.LT.store.service.product;

import com.LT.store.model.product.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product create(Product product);
    Product getById(UUID id);
    List<Product> getAll();
    Product update(UUID id, Product product);
    void delete(UUID id);
    Optional<Product> findByName(String name);
    Optional<Product> findByBarcode(String barcode);
    List<Product> findByCategoryId(UUID categoryId);
}
