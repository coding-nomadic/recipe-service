package com.recipe.server.controller;


import com.recipe.server.entity.Recipe;
import com.recipe.server.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/api/v1/analytics")
@RestController
@CrossOrigin(origins = "*")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    /**
     * @return
     */
    @GetMapping(path = "/popularRecipes")
    public List<Recipe> getPopularRecipes() {
        return analyticsService.generatePopularRecipesReport();
    }

    @GetMapping(path = "/mostCookTime")
    public List<Recipe> getMaxCookRecipes() {
        return analyticsService.mostCookTimeRecipes();
    }
}
