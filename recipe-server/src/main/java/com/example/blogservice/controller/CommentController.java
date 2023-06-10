package com.example.blogservice.controller;

import com.example.blogservice.models.CommentRequest;
import com.example.blogservice.models.CommentResponse;
import com.example.blogservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/recipes/{recipeId}/comments")
    public CommentResponse createComment(@PathVariable(value = "recipeId") long recipeId, @Valid @RequestBody CommentRequest commentDto) {
        return commentService.createComment(recipeId, commentDto);
    }

    @GetMapping("/recipes/{recipeId}/comments/{id}")
    public CommentRequest getCommentById(@PathVariable(value = "recipeId") Long recipeId, @PathVariable(value = "id") Long commentId) {
        return commentService.getCommentById(recipeId, commentId);
    }

    @PutMapping("/recipes/{recipeId}/comments/{commentId}")
    public CommentResponse updateComment(@PathVariable(value = "recipeId") Long recipeId, @PathVariable(value = "commentId") Long commentId, @Valid @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(recipeId, commentId, commentRequest);
    }

    @DeleteMapping("/recipes/{recipeId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "recipeId") Long recipeId, @PathVariable(value = "id") Long commentId) {
        commentService.deleteComment(recipeId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
