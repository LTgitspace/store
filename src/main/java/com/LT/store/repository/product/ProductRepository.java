package com.LT.store.repository.product;

import com.LT.store.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    // Find all products by name (case-insensitive)
    List<Product> findAllByNameContainingIgnoreCase(String name);

    // Find all products by barcode
    List<Product> findAllByBarcode(String barcode);

    // Find all products by category id
    List<Product> findAllByCategoryId(UUID categoryId);
}
