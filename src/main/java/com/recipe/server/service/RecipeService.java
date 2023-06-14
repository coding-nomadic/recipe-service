package com.recipe.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.server.entity.Category;
import com.recipe.server.entity.Recipe;
import com.recipe.server.exceptions.ResourceNotFoundException;
import com.recipe.server.models.CommentResponse;
import com.recipe.server.models.RecipeRequest;
import com.recipe.server.models.RecipeResponse;
import com.recipe.server.repository.CategoryRepository;
import com.recipe.server.repository.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeService {
    @Autowired
    RecipeRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public RecipeResponse saveRecipe(RecipeRequest recipeRequest) {
        categoryRepository.findById(recipeRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category ID not found", "102"));
        Recipe post = postRepository.save(modelMapper.map(recipeRequest, Recipe.class));
        return modelMapper.map(post, RecipeResponse.class);
    }

    public void deleteById(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe ID not found for " + id, "102"));
        postRepository.deleteById(id);
    }

    public RecipeResponse updateRecipe(RecipeRequest recipeRequest, Long id) {
        Recipe post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found", "102"));
        Category category = categoryRepository.findById(recipeRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found", "102"));
        post.setPrepTime(recipeRequest.getPrepTime());
        post.setDescription(recipeRequest.getDescription());
        post.setCookTime(recipeRequest.getCookTime());
        post.setAuthor(recipeRequest.getAuthor());
        post.setUserId(recipeRequest.getUserId());
        post.setCategory(category);
        Recipe postResponse = postRepository.save(post);
        return modelMapper.map(postResponse, RecipeResponse.class);
    }

    public List<Recipe> getAllRecipes() {
        List<RecipeResponse> recipeResponses = new ArrayList<>();
        Set<CommentResponse> commentResponses = new HashSet<>();
        List<Recipe> options = postRepository.findAll();
//        options.stream().filter(s->s.getComments().size()!=0).collect(Collectors.toList()).forEach(System.out::println);
//        if (options.size() != 0) {
//            System.out.println("----------------------------");
//            for (Recipe recipe : options) {
//                System.out.println("---------------------------->>>>");
//                RecipeResponse recipeResponse = new RecipeResponse();
//                recipeResponse.setAuthor(recipe.getAuthor());
//                recipeResponse.setId(recipe.getId());
//                recipeResponse.setDescription(recipe.getDescription());
//                recipeResponse.setCookTime(recipe.getCookTime());
//                recipeResponse.setPrepTime(recipe.getPrepTime());
//                recipeResponse.setUserId(recipe.getUserId());
//                recipeResponse.setAuthor(recipe.getAuthor());
//                recipeResponse.setCategoryId(recipe.getCategory().getId());
//                for (Comment comment : recipe.getComments()) {
//                    CommentResponse commentResponse = new CommentResponse();
//                    commentResponse.setBody(comment.getBody());
//                    commentResponse.setId(comment.getId());
//                    commentResponses.add(commentResponse);
//                }
//                recipeResponse.setComments(commentResponses);
//            }
//            return recipeResponses;
//        } else {
//            throw new ResourceNotFoundException("Empty Recipes", "102");
//        }
                return options;
    }

    public List<RecipeRequest> getPostsByCategory(Long categoryId) {
        List<Recipe> lists = postRepository.findByCategoryId(categoryId);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(lists));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return lists.stream().map(p -> modelMapper.map(p, RecipeRequest.class)).collect(Collectors.toList());
    }
}
