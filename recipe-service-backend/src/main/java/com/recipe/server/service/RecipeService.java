package com.recipe.server.service;

import com.recipe.server.entity.Category;
import com.recipe.server.entity.Recipe;
import com.recipe.server.exceptions.ResourceNotFoundException;
import com.recipe.server.models.RecipeRequest;
import com.recipe.server.models.RecipeResponse;
import com.recipe.server.repository.CategoryRepository;
import com.recipe.server.repository.RecipeRepository;
import com.recipe.server.repository.UserRepository;
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
public class RecipeService {

    private static final String CATEGORY_NOT_FOUND = "Category ID not found";
    private static final String USER_NOT_FOUND = "User ID not found";
    private static final String RECIPE_NOT_FOUND = "Recipe not found";

    @Autowired
    RecipeRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @CacheEvict(value = "recipes", allEntries = true)
    public RecipeResponse saveRecipe(RecipeRequest recipeRequest) {
        validateCategoryExists(recipeRequest.getCategoryId());
        validateUserExists(recipeRequest.getUserId());

        Recipe post = postRepository.save(modelMapper.map(recipeRequest, Recipe.class));
        return modelMapper.map(post, RecipeResponse.class);
    }

    @CacheEvict(value = "recipes", allEntries = true)
    public void deleteById(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND, "102"));
        postRepository.deleteById(id);
    }

    @CacheEvict(value = "recipes", allEntries = true)
    public RecipeResponse updateRecipe(RecipeRequest recipeRequest, Long id) {
        Recipe post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND, "102"));
        validateCategoryExists(recipeRequest.getCategoryId());

        post.setPrepTime(recipeRequest.getPrepTime());
        post.setDescription(recipeRequest.getDescription());
        post.setCookTime(recipeRequest.getCookTime());
        post.setAuthor(recipeRequest.getAuthor());
        post.setUserId(recipeRequest.getUserId());
        post.setCategory(getCategoryById(recipeRequest.getCategoryId()));

        Recipe postResponse = postRepository.save(post);
        return modelMapper.map(postResponse, RecipeResponse.class);
    }

    @Cacheable("recipes")
    public List<Recipe> getAllRecipes() {
        return postRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Cacheable("recipes")
    public List<RecipeRequest> getPostsByCategory(Long categoryId) {
        return postRepository.findByCategoryId(categoryId)
                .stream()
                .map(p -> modelMapper.map(p, RecipeRequest.class))
                .collect(Collectors.toList());
    }

    private void validateCategoryExists(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND, "102"));
    }

    private void validateUserExists(String userId) {
        userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND, "102"));
    }

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND, "102"));
    }
}
