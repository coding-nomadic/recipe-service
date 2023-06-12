package com.recipe.server.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CommentRequest implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
