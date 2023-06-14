package com.recipe.server.controller;

import com.recipe.server.models.CategoryRequest;
import com.recipe.server.models.CategoryResponse;
import com.recipe.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.IOException;
import java.util.List;

@RequestMapping(path = "/api/v1/categories")
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @param categoryRequest
     * @return
     * @throws IOException
     */
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }

    @PutMapping(path = "{id}")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long id) {
        return categoryService.updateCategory(categoryRequest, id);
    }

    /**
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("{id}")
    public CategoryRequest getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    /**
     * @return
     */
    @GetMapping
    public List<CategoryRequest> getCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * @param id
     * @return
     * @throws IOException
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
