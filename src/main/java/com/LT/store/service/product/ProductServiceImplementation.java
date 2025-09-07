package com.LT.store.service.product;

import com.LT.store.model.product.Product;
import com.LT.store.dto.product.ProductDTO;
import com.LT.store.model.category.Category;
import com.LT.store.repository.category.CategoryRepository;
import com.LT.store.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private ProductDTO mapToDTO(Product product) {
        if (product == null) return null;
        return ProductDTO.builder()
                .barcode(product.getBarcode())
                .name(product.getName())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .build();
    }

    private Product mapToEntity(ProductDTO dto) {
        if (dto == null) return null;
        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        }
        return Product.builder()
                .barcode(dto.getBarcode())
                .name(dto.getName())
                .category(category)
                .build();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product create(ProductDTO dto) {
        Product product = mapToEntity(dto);
        return productRepository.save(product);
    }

    @Override
    public Product getById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductDTO getDTOById(UUID id) {
        return mapToDTO(getById(id));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<ProductDTO> getAllDTO() {
        return productRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public Product update(UUID id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(product.getName());
        existing.setBarcode(product.getBarcode());
        existing.setCategory(product.getCategory());
        return productRepository.save(existing);
    }

    public ProductDTO update(UUID id, ProductDTO dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(dto.getName());
        existing.setBarcode(dto.getBarcode());
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            existing.setCategory(category);
        }
        Product updated = productRepository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }

    public Optional<ProductDTO> findDTOByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name).map(this::mapToDTO);
    }

    @Override
    public Optional<Product> findByBarcode(String barcode) {
        return productRepository.findAllByBarcode(barcode);
    }

    public Optional<ProductDTO> findDTOByBarcode(String barcode) {
        return productRepository.findAllByBarcode(barcode).map(this::mapToDTO);
    }

    @Override
    public List<Product> findByCategoryId(UUID categoryId) {
        return productRepository.findAllByCategoryId(categoryId)
                .map(List::of)
                .orElse(List.of());
    }

    public List<ProductDTO> findDTOByCategoryId(UUID categoryId) {
        return productRepository.findAllByCategoryId(categoryId)
                .map(product -> List.of(mapToDTO(product)))
                .orElse(List.of());
    }
}
