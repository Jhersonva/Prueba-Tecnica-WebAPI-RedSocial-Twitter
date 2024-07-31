package com.apiweb.redsocial.domain.repository;

import com.apiweb.redsocial.domain.model.Post;

import java.util.List;

/**
 * Interfaz de repositorio para gestionar publicaciones.
 */
public interface PostRepository {
    void save(Post post);
    List<Post> findAll();
}
