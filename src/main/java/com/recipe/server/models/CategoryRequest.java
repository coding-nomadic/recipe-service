package com.recipe.server.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryRequest implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
}
