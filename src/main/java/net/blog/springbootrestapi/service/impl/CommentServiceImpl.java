package net.blog.springbootrestapi.service.impl;

import net.blog.springbootrestapi.dto.CommentDto;
import net.blog.springbootrestapi.entity.Comment;
import net.blog.springbootrestapi.entity.Post;
import net.blog.springbootrestapi.exception.ResourceNotFoundException;
import net.blog.springbootrestapi.repository.CommentRepository;
import net.blog.springbootrestapi.repository.PostRepository;
import net.blog.springbootrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = toEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // set post to comment entity
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return toDto(newComment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
       return null;
    }

    @Override
    public List<CommentDto> getAllComments(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> toDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        return null;
    }

    @Override
    public CommentDto deleteComment(long postId, long commentId) {
        return null;
    }

    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    public Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

}
