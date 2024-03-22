
package com.recipe.server;

import com.recipe.server.controller.CategoryController;
import com.recipe.server.controller.RecipeController;
import com.recipe.server.entity.Recipe;
import com.recipe.server.models.CategoryRequest;
import com.recipe.server.models.CategoryResponse;
import com.recipe.server.models.RecipeRequest;
import com.recipe.server.models.RecipeResponse;
import com.recipe.server.service.CategoryService;
import com.recipe.server.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryControllerTests {
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void test_createCategory(){
        CategoryResponse categoryResponse=new CategoryResponse();
        Mockito.when(categoryService.saveCategory(ArgumentMatchers.any())).thenReturn(categoryResponse);
        Assertions.assertNotNull(categoryController.createCategory(ArgumentMatchers.any()));
        Mockito.verify(categoryService,Mockito.timeout(1)).saveCategory(ArgumentMatchers.any());
    }

    @Test
    public void test_updateCategory(){
        CategoryResponse response=new CategoryResponse();
        Mockito.when(categoryService.updateCategory(ArgumentMatchers.any(),ArgumentMatchers.anyLong())).thenReturn(response);
        Assertions.assertNotNull(categoryController.updateCategory(ArgumentMatchers.any(),ArgumentMatchers.anyLong()));
    }

    @Test
    public void test_getAllCategories(){
        List<CategoryRequest>list=new ArrayList<>();
        Mockito.when(categoryService.getAllCategories()).thenReturn(list);
        Assertions.assertNotNull(categoryController.getCategories());
        Mockito.verify(categoryService,Mockito.times(1)).getAllCategories();
    }
    @Test
    public void test_getByCategoryId(){
        CategoryRequest categoryRequest=new CategoryRequest();
        Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(categoryRequest);
        Assertions.assertNotNull(categoryController.getCategoryById(ArgumentMatchers.anyLong()));
        Mockito.verify(categoryService,Mockito.times(1)).getCategoryById(ArgumentMatchers.anyLong());
    }

    @Test
    public void test_deleteRecipe(){
        categoryController.deleteById(12L);
    }
}
