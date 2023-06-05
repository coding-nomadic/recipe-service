package com.example.recipe.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecipeRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentRequest> comments;

    private Long categoryId;

}
