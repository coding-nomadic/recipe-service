package com.recipe.server.service;

import com.recipe.server.entity.Recipe;
import com.recipe.server.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnalyticsService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public AnalyticsService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> generatePopularRecipesReport() {
        Optional<Integer> maxCommentCount = recipeRepository.findAll()
                .stream()
                .map(r -> r.getComments().size())
                .max(Integer::compareTo);
        
        if (maxCommentCount.isPresent()) {
            log.info("Maximum count from the recipe records: " + maxCommentCount.get());
            return recipeRepository.findAll()
                    .stream()
                    .filter(r -> r.getComments().size() == maxCommentCount.get())
                    .collect(Collectors.toList());
        } else {
            log.warn("No recipes found");
            return List.of();
        }
    }

    public List<Recipe> mostCookTimeRecipes() {
        Optional<Integer> maxCookTime = recipeRepository.findAll()
                .stream()
                .map(r -> Integer.parseInt(r.getCookTime()))
                .max(Integer::compareTo);

        if (maxCookTime.isPresent()) {
            log.info("Maximum cook time from the recipe records: " + maxCookTime.get());
            return recipeRepository.findAll()
                    .stream()
                    .filter(r -> Integer.parseInt(r.getCookTime()) == maxCookTime.get())
                    .collect(Collectors.toList());
        } else {
            log.warn("No recipes found");
            return List.of();
        }
    }
}
