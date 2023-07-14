
package com.recipe.server;

import com.recipe.server.controller.RecipeController;
import com.recipe.server.entity.Recipe;
import com.recipe.server.models.RecipeRequest;
import com.recipe.server.models.RecipeResponse;
import com.recipe.server.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RecipeControllerTests {
    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;
    @Test
    public void test_createRecipe(){
        RecipeResponse response=new RecipeResponse();
        Mockito.when(recipeService.saveRecipe(ArgumentMatchers.any())).thenReturn(response);
        Assertions.assertNotNull(recipeController.createRecipe(getRecipeRequest()));
        Assertions.assertEquals(201,recipeController.createRecipe(getRecipeRequest()).getStatusCode().value());
    }

    @Test
    public void test_updateRecipe(){
        RecipeResponse response=new RecipeResponse();
        Mockito.when(recipeService.updateRecipe(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(response);
        Assertions.assertNotNull(recipeController.updateRecipe(getRecipeRequest(),12L));
        Mockito.verify(recipeService,Mockito.times(1)).updateRecipe(ArgumentMatchers.any(),ArgumentMatchers.any());
    }

    @Test
    public void test_getAllRecipes(){
        Recipe response=new Recipe();
        List<Recipe>list=new ArrayList<>();
        list.add(response);
        Mockito.when(recipeService.getAllRecipes()).thenReturn(list);
        Assertions.assertNotNull(recipeController.getAllRecipes());
        Mockito.verify(recipeService,Mockito.times(1)).getAllRecipes();
    }

    @Test
    public void test_deleteRecipe(){
        recipeController.deleteRecipe(12L);
    }


    private RecipeRequest getRecipeRequest(){
        RecipeRequest recipeRequest=new RecipeRequest();
        recipeRequest.setId(2L);
        recipeRequest.setAuthor("");
        recipeRequest.setDescription("");
        recipeRequest.setIngredient("");
        recipeRequest.setCategoryId(23L);
        recipeRequest.setCookTime("");
        recipeRequest.setUserId("");
        return recipeRequest;
    }
}
