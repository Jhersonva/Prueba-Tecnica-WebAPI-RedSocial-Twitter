package com.apiweb.redsocial.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de modelo de dominio que representa a un usuario.
 */
public class User {
    private String name;
    private List<User> following = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void follow(User user) {
        following.add(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
