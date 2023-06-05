package com.example.blogservice.models;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
