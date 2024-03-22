package com.recipe.server.controller;

import com.recipe.server.entity.Recipe;
import com.recipe.server.models.RecipeRequest;
import com.recipe.server.models.RecipeResponse;
import com.recipe.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@Validated @RequestBody RecipeRequest recipeRequest) {
        RecipeResponse recipeResponse = recipeService.saveRecipe(recipeRequest);
        return new ResponseEntity<>(recipeResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public RecipeResponse updateRecipe(
            @Validated @RequestBody RecipeRequest recipeRequest,
            @PathVariable Long id
    ) {
        return recipeService.updateRecipe(recipeRequest, id);
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
