package com.recipe.server.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long id;

    private int ratings;
    private String body;
}
