package com.apiweb.redsocial.application.service;

import com.apiweb.redsocial.domain.model.Post;
import com.apiweb.redsocial.domain.model.User;
import com.apiweb.redsocial.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Servicio de Post para el manejo de operaciones relacionadas con el Post.
 */
@Service
public class PostService {

    @Autowired
    private final UserRepository userRepository;

    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createPost(String username, String content) {
        User user = userRepository.findByName(username).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = new Post(content, LocalDateTime.now());
        user.addPost(post);
        userRepository.save(user);
    }
}
