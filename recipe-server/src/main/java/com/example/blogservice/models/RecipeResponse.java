package com.example.blogservice.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class RecipeResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long id;


    private String description;

    private String userId;

    private String author;

    private String prepTime;

    private String cookTime;
    private Set<CommentResponse> comments;
    private Long categoryId;

}
