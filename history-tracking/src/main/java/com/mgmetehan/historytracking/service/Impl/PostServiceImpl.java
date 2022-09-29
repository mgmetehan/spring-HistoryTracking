package com.mgmetehan.historytracking.service.Impl;

import com.mgmetehan.historytracking.core.exception.NotFoundException;
import com.mgmetehan.historytracking.core.repository.BaseJpaRepository;
import com.mgmetehan.historytracking.core.service.AbstractEntityService;
import com.mgmetehan.historytracking.model.Post;
import com.mgmetehan.historytracking.repository.PostRepository;
import com.mgmetehan.historytracking.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl extends AbstractEntityService<Post, Long> implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post save(Post newPost) {
        postRepository.save(newPost);
        log.debug("PostSave running... {}", newPost);
        return null;
    }

    @Override
    public Post getById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new NotFoundException("Not Found Exception"));
    }

    @Override
    public Post update(Long id, Post updateDto) {
        var post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));
        post.setTitle(updateDto.getTitle());
        post.setText(updateDto.getText());
        var updatePost = postRepository.save(post);
        return updatePost;
    }

    @Override
    public BaseJpaRepository<Post, Long> getJpaRepository() {
        return null;
    }
}
