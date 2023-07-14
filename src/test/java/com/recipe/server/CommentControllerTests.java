
package com.recipe.server;

import com.recipe.server.controller.CommentController;
import com.recipe.server.models.CommentRequest;
import com.recipe.server.models.CommentResponse;
import com.recipe.server.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentControllerTests {
    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    public void test_createComment() {
        CommentResponse response = new CommentResponse();
        Mockito.when(commentService.createComment(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(response);
        Assertions.assertNotNull(commentController.createComment(ArgumentMatchers.anyLong(), ArgumentMatchers.any()));
    }

    @Test
    public void test_updateComment() {
        CommentResponse response = new CommentResponse();
        Mockito.when(commentService.updateComment(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(response);
        Assertions.assertNotNull(commentController.updateComment(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any()));
    }

    @Test
    public void test_getCommentById() {
        CommentRequest commentRequest = new CommentRequest();
        Mockito.when(commentService.getCommentById(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(commentRequest);
        Assertions.assertNotNull(commentController.getCommentById(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()));
    }

    @Test
    public void test_deleteComment() {
        Assertions.assertNotNull(commentController.deleteComment(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()));
    }
}
