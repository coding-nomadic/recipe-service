package com.recipe.server.service;

import com.recipe.server.entity.Recipe;
import com.recipe.server.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AnalyticsService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> generatePopularRecipesReport() {
        int maxCommentCount = recipeRepository.findAll().stream().mapToInt(s -> s.getComments().size()).summaryStatistics().getMax();
        log.info("Maximum count from the recipe records : " + maxCommentCount);
        return recipeRepository.findAll().stream().filter(s -> s.getComments().size() == maxCommentCount).collect(Collectors.toList());
    }

    public List<Recipe> mostCookTimeRecipes() {
        int maxCookTime = recipeRepository.findAll().stream().mapToInt(i -> Integer.valueOf(i.getCookTime())).summaryStatistics().getMax();
        log.info("Maximum cook Time from the recipe records : " + maxCookTime);
        return recipeRepository.findAll().stream().filter(s -> Integer.valueOf(s.getCookTime()) == maxCookTime).collect(Collectors.toList());
    }
}
