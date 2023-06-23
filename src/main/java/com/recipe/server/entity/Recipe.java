package com.recipe.server.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "tbl_recipe")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "prep_time", nullable = false)
    private String prepTime;

    @Column(name = "cook_time", nullable = false)
    private String cookTime;

    @OneToMany(mappedBy = "recipe",fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();
    private String ingredient;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
