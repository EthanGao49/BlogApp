package net.blog.springbootrestapi.controller;

import jakarta.validation.Valid;
import net.blog.springbootrestapi.dto.CommentDto;
import net.blog.springbootrestapi.entity.Comment;
import net.blog.springbootrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CommentController {
    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(name = "postId") long postId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsById(@PathVariable(name = "postId") long postId) {
        return commentService.getAllComments(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId
    ) {
        CommentDto commentDto =  commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId
    ) {
        CommentDto deletedComment = commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(deletedComment, HttpStatus.OK);
    }
}
