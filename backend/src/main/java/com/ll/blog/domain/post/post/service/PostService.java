package com.ll.blog.domain.post.post.service;

import com.ll.blog.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
}
