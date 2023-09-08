package com.recipe.server.service;

import com.recipe.server.entity.Category;
import com.recipe.server.exceptions.ResourceNotFoundException;
import com.recipe.server.models.CategoryRequest;
import com.recipe.server.models.CategoryResponse;
import com.recipe.server.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryResponse.class);
    }

    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category ID not found", "102"));
        category.setName(categoryRequest.getName());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryResponse.class);
    }

    @Cacheable("categories")
    public CategoryRequest getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category ID not found", "102"));
        return modelMapper.map(category, CategoryRequest.class);
    }

    @Cacheable("categories")
    public List<CategoryRequest> getAllCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryRequest.class))
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "categories", allEntries = true)
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
