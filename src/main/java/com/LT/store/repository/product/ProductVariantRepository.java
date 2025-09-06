package com.LT.store.repository.product;

import com.LT.store.model.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, UUID> {
    // Find all variants by color
    List<ProductVariant> findAllByColor(String color);

    // Find all variants by size
    List<ProductVariant> findAllBySize(int size);

    // Find all variants by price range
    List<ProductVariant> findAllByPriceBetween(double minPrice, double maxPrice);

    // Find all variants by description containing (case-insensitive)
    List<ProductVariant> findAllByDescriptionContainingIgnoreCase(String description);
}
