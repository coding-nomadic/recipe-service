package com.example.recipe.controllers;

import com.example.recipe.models.RecipeRequest;
import com.example.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/post")
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody RecipeRequest recipeRequest) {
        PostResponse postResponse = postService.savePost(postRequest);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }
}
