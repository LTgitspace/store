package com.LT.store.service.category;

import com.LT.store.dto.category.CategoryCreation;
import com.LT.store.dto.category.CategoryDTO;
import com.LT.store.model.category.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    CategoryDTO create(CategoryCreation creation);
    CategoryDTO getById(UUID id);
    List<CategoryDTO> getAll();
    CategoryDTO update(UUID id, CategoryCreation creation);
    void delete(UUID id);
    Optional<CategoryDTO> findByName(String name);
    Optional<CategoryDTO> searchByName(String name);
}
