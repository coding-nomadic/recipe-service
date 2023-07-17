
package com.recipe.server;

import com.recipe.server.controller.AnalyticsController;
import com.recipe.server.entity.Recipe;
import com.recipe.server.service.AnalyticsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AnalyticsControllerTests {
    @Mock
    private AnalyticsService analyticsService;

    @InjectMocks
    private AnalyticsController analyticsController;


    @Test
    public void test_getPopularRecipes() {
        List<Recipe> list = new ArrayList<>();
        Mockito.when(analyticsService.generatePopularRecipesReport()).thenReturn(list);
        Assertions.assertNotNull(analyticsController.getPopularRecipes());
        Mockito.verify(analyticsService, Mockito.times(1)).generatePopularRecipesReport();
    }

    @Test
    public void test_getMaxCookRecipess() {
        List<Recipe> list = new ArrayList<>();
        Mockito.when(analyticsService.mostCookTimeRecipes()).thenReturn(list);
        Assertions.assertNotNull(analyticsController.getMaxCookRecipes());
        Mockito.verify(analyticsService, Mockito.times(1)).mostCookTimeRecipes();
    }
}
