package com.recipe.server.controller;

import com.recipe.server.entity.Recipe;
import com.recipe.server.models.RecipeRequest;
import com.recipe.server.models.RecipeResponse;
import com.recipe.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RequestMapping(value = "/api/v1/recipes")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
