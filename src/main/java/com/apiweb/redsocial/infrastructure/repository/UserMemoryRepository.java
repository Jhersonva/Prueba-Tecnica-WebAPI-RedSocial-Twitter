package com.apiweb.redsocial.infrastructure.repository;

import com.apiweb.redsocial.domain.model.User;
import com.apiweb.redsocial.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Implementación en memoria del UserRepository.
 */
@Repository
public class UserMemoryRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(users.get(name));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }



    public void clear() {
        users.clear();
    }
}
