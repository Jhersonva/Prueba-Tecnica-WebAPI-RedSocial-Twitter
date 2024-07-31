package com.apiweb.redsocial.infrastructure.api.controller;

import com.apiweb.redsocial.application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador REST para operaciones post-relacionadas.
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void createPost(@RequestParam String username, @RequestBody Map<String, String> body) {
        String content = body.get("content");
        postService.createPost(username, content);
    }
}
