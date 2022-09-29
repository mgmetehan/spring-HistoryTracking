package com.mgmetehan.historytracking.service.Impl;

import com.mgmetehan.historytracking.model.Post;
import com.mgmetehan.historytracking.repository.PostRepository;
import com.mgmetehan.historytracking.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post save(Post newPost) {
        return null;
    }

    @Override
    public Post getById(Long id) {
        return null;
    }
}
