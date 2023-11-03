package net.blog.springbootrestapi.service;

import net.blog.springbootrestapi.dto.PostDto;
import net.blog.springbootrestapi.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();

    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);
    PostDto deletePost(long id);
}
