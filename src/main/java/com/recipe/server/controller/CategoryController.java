package com.recipe.server.controller;

import com.recipe.server.models.CategoryRequest;
import com.recipe.server.models.CategoryResponse;
import com.recipe.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Create a new category.
     *
     * @param categoryRequest The category request data.
     * @return The created category response.
     */
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }

    /**
     * Update an existing category by ID.
     *
     * @param id             The ID of the category to update.
     * @param categoryRequest The updated category request data.
     * @return The updated category response.
     */
    @PutMapping(path = "{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryRequest, id);
    }

    /**
     * Get a category by ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The category request data.
     */
    @GetMapping("{id}")
    public CategoryRequest getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    /**
     * Get all categories.
     *
     * @return A list of category request data.
     */
    @GetMapping
    public List<CategoryRequest> getCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Delete a category by ID.
     *
     * @param id The ID of the category to delete.
     * @return A response entity with a no-content status.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
