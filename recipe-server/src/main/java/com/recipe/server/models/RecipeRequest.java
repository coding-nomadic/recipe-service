package com.recipe.server.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
public class RecipeRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private long id;
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;
    private Set<CommentRequest> comments;
    @NotEmpty
    @Size(min = 10, message = "Recipe user id should have at least 10 characters")
    private String userId;
    @NotEmpty
    @Size(min = 10, message = "Recipe author should have at least 10 characters")
    private String author;
    @NotEmpty
    @Size(min = 10, message = "Recipe prep time should have at least 10 characters")
    private String prepTime;
    @NotEmpty
    @Size(min = 10, message = "Recipe cook time should have at least 10 characters")
    private String cookTime;
    private Long categoryId;

}
