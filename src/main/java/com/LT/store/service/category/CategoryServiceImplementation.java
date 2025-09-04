package com.LT.store.service.category;

import com.LT.store.dto.category.CategoryDTO;
import com.LT.store.dto.category.CategoryCreation;
import com.LT.store.model.category.Category;
import com.LT.store.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository categoryRepository;

    private CategoryDTO mapToDTO(Category category) {
        if (category == null) return null;
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private Category mapToEntity(CategoryCreation creation) {
        if (creation == null) return null;
        return Category.builder()
                .name(creation.getName())
                .build();
    }

    @Override
    public CategoryDTO create(CategoryCreation creation) {
        if (categoryRepository.existsByName(creation.getName())) {
            throw new RuntimeException("Category name already exists");
        }
        Category category = mapToEntity(creation);
        Category saved = categoryRepository.save(category);
        return mapToDTO(saved);
    }

    @Override
    public CategoryDTO getById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public CategoryDTO update(UUID id, CategoryCreation creation) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(creation.getName());
        // Add other fields as needed
        Category updated = categoryRepository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryDTO> findByName(String name) {
        return categoryRepository.findByName(name).map(this::mapToDTO);
    }

    @Override
    public Optional<CategoryDTO> searchByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name).map(this::mapToDTO);
    }
}
