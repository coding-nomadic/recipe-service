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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        Category categoryResponse = categoryRepository.save(category);
        return modelMapper.map(categoryResponse, CategoryResponse.class);
    }
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                                        () -> new ResourceNotFoundException("Category Id not found", "102"));
        category.setName(categoryRequest.getName());
        category.setId(id);
        Category categoryResponse = categoryRepository.save(category);
        return modelMapper.map(categoryResponse, CategoryResponse.class);
    }
    @Cacheable("categories")
    public CategoryRequest getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                                        () -> new ResourceNotFoundException("Category ID not found", "102"));
        return modelMapper.map(category, CategoryRequest.class);
    }
    @Cacheable("categories")
    public List<CategoryRequest> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryRequest.class))
                                        .collect(Collectors.toList());
    }
    @CacheEvict(value = "categories", allEntries = true)
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
