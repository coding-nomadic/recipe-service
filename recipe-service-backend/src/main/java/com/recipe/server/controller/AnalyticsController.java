package com.recipe.server.controller;

import com.recipe.server.entity.Recipe;
import com.recipe.server.service.AnalyticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping(path = "/popularRecipes")
    public List<Recipe> getPopularRecipes() {
        return analyticsService.generatePopularRecipesReport();
    }

    @GetMapping(path = "/mostCookTime")
    public List<Recipe> getMaxCookRecipes() {
        return analyticsService.mostCookTimeRecipes();
    }
}
