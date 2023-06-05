package com.example.blogservice.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    //@Column(name = "ratings", nullable = false)
    //private List<Integer> ratings;

    //@Column(name = "ingredient_id", nullable = false)
    //private String ingredient_id;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ingredient_id",insert="false",update="false")
//    private Ingredient ingredient;
}
