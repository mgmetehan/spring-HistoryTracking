package com.mgmetehan.historytracking.service;

import com.mgmetehan.historytracking.core.service.BaseEntityService;
import com.mgmetehan.historytracking.model.Post;

public interface PostService extends BaseEntityService<Post, Long> {
    Post save(Post newPost);

    Post getById(Long id);

    Post update(Long id, Post updateDto);
}
