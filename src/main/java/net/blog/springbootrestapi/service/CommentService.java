package net.blog.springbootrestapi.service;

import net.blog.springbootrestapi.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
    List<CommentDto> getAllComments(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto deleteComment(long postId, long commentId);
}
