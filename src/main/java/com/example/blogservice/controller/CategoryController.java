package com.example.blogservice.controller;

import com.example.blogservice.models.CategoryRequest;
import com.example.blogservice.models.CategoryResponse;
import com.example.blogservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping(path = "/api/v1/category")
@RestController
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
