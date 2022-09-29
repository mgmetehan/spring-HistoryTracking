package com.mgmetehan.historytracking.service;

import com.mgmetehan.historytracking.model.Post;

public interface PostService {
    Post save(Post newPost);

    Post getById(Long id);
}
