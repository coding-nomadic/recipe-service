package com.example.blogservice.controller;

import com.example.blogservice.entity.Recipe;
import com.example.blogservice.models.RecipeRequest;
import com.example.blogservice.models.RecipeResponse;
import com.example.blogservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/recipes")
@RestController
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest postRequest) {
        RecipeResponse postResponse = recipeService.saveRecipe(postRequest);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public RecipeResponse updateRecipe(@RequestBody RecipeRequest postRequest, @PathVariable Long id) {
        return recipeService.updateRecipe(postRequest, id);
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/category/{categoryId}")
    public List<RecipeRequest> getPostsByCategory(@PathVariable("categoryId") Long categoryId) {
        return recipeService.getPostsByCategory(categoryId);
    }
}
