package com.recipe.server.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_comment")
@Data
public class Comment  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String body;

    private int ratings;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
