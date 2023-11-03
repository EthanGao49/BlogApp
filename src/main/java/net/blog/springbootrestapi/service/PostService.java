package net.blog.springbootrestapi.service;

import net.blog.springbootrestapi.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
}
