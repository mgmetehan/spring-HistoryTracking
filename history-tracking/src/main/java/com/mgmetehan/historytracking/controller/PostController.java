package com.mgmetehan.historytracking.controller;

import com.mgmetehan.historytracking.model.Post;
import com.mgmetehan.historytracking.service.PostService;
import com.mgmetehan.historytracking.shared.PostEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PostEndpoints.POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post newPost) {
        return new ResponseEntity<>(postService.save(newPost), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post updateDto) {
        return ResponseEntity.ok(postService.update(id, updateDto));
    }
}
