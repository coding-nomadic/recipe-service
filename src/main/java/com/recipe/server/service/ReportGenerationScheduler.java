package com.recipe.server.service;

import com.recipe.server.entity.Recipe;
import com.recipe.server.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportGenerationScheduler {

    @Autowired
    private AnalyticsService analyticsService;

    @Scheduled(cron = "0 0 0 * * ?") // Runs once per day at midnight
    public void generateReports() {
        List<Recipe> recipeList=analyticsService.generatePopularRecipesReport();

    }
}
