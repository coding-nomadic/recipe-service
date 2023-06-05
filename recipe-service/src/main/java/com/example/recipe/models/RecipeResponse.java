package com.example.recipe.models;

public class RecipeResponse {
      *
              */
    private static final long serialVersionUID = 1L;

    private long id;

    private String title;

    private String description;

    private String content;

    private Set<CommentRequest> comments;

    private Long categoryId;
}
