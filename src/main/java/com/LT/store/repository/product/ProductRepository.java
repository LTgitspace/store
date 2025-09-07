package com.LT.store.repository.product;

import com.LT.store.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findById(UUID id);
    
    // Find all products by name (case-insensitive)
    Optional<Product> findAllByNameContainingIgnoreCase(String name);

    // Find all products by barcode
    Optional<Product> findAllByBarcode(String barcode);

    // Find all products by category
    Optional<Product> findAllByCategoryId(UUID categoryId);
}
