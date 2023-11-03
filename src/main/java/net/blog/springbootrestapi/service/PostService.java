package net.blog.springbootrestapi.service;

import net.blog.springbootrestapi.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
