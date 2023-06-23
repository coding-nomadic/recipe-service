package com.recipe.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    private static final String POST_NOT_FOUND = "Recipe not found";
    private static final String COMMENT_NOT_FOUND = "Comment not found";
    private static final String COMMENT_NOT_BELONG = "Comment does not belong to Recipe";
    @Autowired
    private ModelMapper mapper;

    @Autowired
    RecipeRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @CacheEvict(value = "comments", allEntries = true)
    public CommentResponse createComment(long postId, CommentRequest commentRequest) {
        Comment comment = mapper.map(commentRequest, Comment.class);
        Recipe post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Recipe ID not found", "102"));
        comment.setRecipe(post);
        Comment commentResponse = commentRepository.save(comment);
        return mapper.map(commentResponse, CommentResponse.class);
    }

    @Cacheable("comments")
    public CommentRequest getCommentById(Long postId, Long commentId) {
        Recipe post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST_NOT_FOUND, "102"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(POST_NOT_FOUND, "102"));
        if (!comment.getRecipe().getId().equals(post.getId())) {
            throw new RecipeServiceException(COMMENT_NOT_BELONG, "102");
        }
        return mapper.map(comment, CommentRequest.class);
    }

    @CacheEvict(value = "comments", allEntries = true)
    public CommentResponse updateComment(Long postId, long commentId, CommentRequest commentRequest) {
        Recipe post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST_NOT_FOUND, "102"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(COMMENT_NOT_FOUND, "id"));
        if (!comment.getRecipe().getId().equals(post.getId())) {
            throw new RecipeServiceException(COMMENT_NOT_BELONG, "102");
        }
        comment.setBody(commentRequest.getBody());
        comment.setRatings(commentRequest.getRatings());
        Comment commentResponse = commentRepository.save(comment);
        return mapper.map(commentResponse, CommentResponse.class);
    }
    @CacheEvict(value = "comments", allEntries = true)
    public void deleteComment(Long postId, Long commentId) {
        Recipe post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST_NOT_FOUND, "id"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(COMMENT_NOT_FOUND, "id"));
        if (!comment.getRecipe().getId().equals(post.getId())) {
            throw new RecipeServiceException(COMMENT_NOT_BELONG, "102");
        }
        commentRepository.delete(comment);
    }
}
