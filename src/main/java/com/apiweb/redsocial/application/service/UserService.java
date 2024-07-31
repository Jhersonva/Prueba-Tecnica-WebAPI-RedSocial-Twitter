package com.apiweb.redsocial.application.service;


import com.apiweb.redsocial.domain.model.User;
import com.apiweb.redsocial.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de servicio para gestionar usuarios y publicaciones.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String name) {
        User user = new User(name);
        userRepository.save(user);
    }

    public void followUser(String followerName, String followeeName) {
        User follower = userRepository.findByName(followerName).orElseThrow(() -> new RuntimeException("Follower not found"));
        User followee = userRepository.findByName(followeeName).orElseThrow(() -> new RuntimeException("Followee not found"));
        follower.follow(followee);
        userRepository.save(follower);
    }

    public List<String> getWall(String username) {
        User user = userRepository.findByName(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<String> wall = new ArrayList<>();

        user.getFollowing().forEach(followedUser -> {
            followedUser.getPosts().forEach(post -> {
                String formattedPost = String.format("%s @%s @%s", post.getContent(), followedUser.getName(), post.getTimestamp().toLocalDate());
                wall.add(formattedPost);
            });
        });

        return wall;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }
}
