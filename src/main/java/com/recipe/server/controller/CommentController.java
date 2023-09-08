package com.recipe.server.controller;

import com.recipe.server.models.CommentRequest;
import com.recipe.server.models.CommentResponse;
import com.recipe.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/recipes/{recipeId}/comments")
    public CommentResponse createComment(
            @PathVariable(value = "recipeId") long recipeId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        return commentService.createComment(recipeId, commentRequest);
    }

    @GetMapping("/recipes/{recipeId}/comments/{commentId}")
    public CommentRequest getCommentById(
            @PathVariable(value = "recipeId") Long recipeId,
            @PathVariable(value = "commentId") Long commentId
    ) {
        return commentService.getCommentById(recipeId, commentId);
    }

    @PutMapping("/recipes/{recipeId}/comments/{commentId}")
    public CommentResponse updateComment(
            @PathVariable(value = "recipeId") Long recipeId,
            @PathVariable(value = "commentId") Long commentId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        return commentService.updateComment(recipeId, commentId, commentRequest);
    }

    @DeleteMapping("/recipes/{recipeId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable(value = "recipeId") Long recipeId,
            @PathVariable(value = "commentId") Long commentId
    ) {
        commentService.deleteComment(recipeId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
