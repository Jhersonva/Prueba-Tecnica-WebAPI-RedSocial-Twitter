package com.apiweb.redsocial.domain.repository;

import com.apiweb.redsocial.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para la gestión de usuarios.
 */
public interface UserRepository {

    void save(User user);
    Optional<User> findByName(String name);

    List<User> findAll();

}
