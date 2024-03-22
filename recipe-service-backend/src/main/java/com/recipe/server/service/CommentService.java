package com.recipe.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.server.entity.Comment;
import com.recipe.server.entity.Recipe;
import com.recipe.server.exceptions.RecipeServiceException;
import com.recipe.server.exceptions.ResourceNotFoundException;
import com.recipe.server.models.CommentRequest;
import com.recipe.server.models.CommentResponse;
import com.recipe.server.repository.CommentRepository;
import com.recipe.server.repository.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private static final String RECIPE_NOT_FOUND = "Recipe not found";
    private static final String COMMENT_NOT_FOUND = "Comment not found";
    private static final String COMMENT_NOT_BELONG = "Comment does not belong to Recipe";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @CacheEvict(value = "comments", allEntries = true)
    public CommentResponse createComment(long recipeId, CommentRequest commentRequest) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND, "102"));

        Comment comment = mapper.map(commentRequest, Comment.class);
        comment.setRecipe(recipe);

        Comment savedComment = commentRepository.save(comment);
        return mapper.map(savedComment, CommentResponse.class);
    }

    @Cacheable("comments")
    public CommentRequest getCommentById(Long recipeId, Long commentId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND, "102"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT_NOT_FOUND, "102"));

        validateCommentBelongs(recipe, comment);

        return mapper.map(comment, CommentRequest.class);
    }

    @CacheEvict(value = "comments", allEntries = true)
    public CommentResponse updateComment(Long recipeId, long commentId, CommentRequest commentRequest) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND, "102"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT_NOT_FOUND, "id"));

        validateCommentBelongs(recipe, comment);

        comment.setBody(commentRequest.getBody());
        comment.setRatings(commentRequest.getRatings());

        Comment updatedComment = commentRepository.save(comment);
        return mapper.map(updatedComment, CommentResponse.class);
    }

    @CacheEvict(value = "comments", allEntries = true)
    public void deleteComment(Long recipeId, Long commentId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(RECIPE_NOT_FOUND, "id"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT_NOT_FOUND, "id"));

        validateCommentBelongs(recipe, comment);

        commentRepository.delete(comment);
    }

    private void validateCommentBelongs(Recipe recipe, Comment comment) {
        if (!comment.getRecipe().getId().equals(recipe.getId())) {
            throw new RecipeServiceException(COMMENT_NOT_BELONG, "102");
        }
    }
}
