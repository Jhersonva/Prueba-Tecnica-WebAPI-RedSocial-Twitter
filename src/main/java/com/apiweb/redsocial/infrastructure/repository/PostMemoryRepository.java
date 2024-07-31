package com.apiweb.redsocial.infrastructure.repository;

import com.apiweb.redsocial.domain.model.Post;
import com.apiweb.redsocial.domain.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación en memoria del PostRepository.
 */
@Repository
public class PostMemoryRepository implements PostRepository {
    private final Map<String, Post> posts = new HashMap<>();

    @Override
    public void save(Post post) {
        posts.put(post.getContent(), post);
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    public void clear() {
        posts.clear();
    }
}
