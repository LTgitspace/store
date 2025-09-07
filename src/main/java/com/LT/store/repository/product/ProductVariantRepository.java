package com.LT.store.repository.product;

import com.LT.store.model.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, UUID> {
    // Find all variants by color
    Optional<ProductVariant> findAllByColor(String color);

    // Find all variants by size
    Optional<ProductVariant> findAllBySize(int size);

    // Find all variants by price range
    Optional<ProductVariant> findAllByPriceBetween(double minPrice, double maxPrice);

    // Find all variants by description containing (case-insensitive)
    Optional<ProductVariant> findAllByDescriptionContainingIgnoreCase(String description);

    Optional<ProductVariant> findByProductId(UUID id);
}

