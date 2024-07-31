package com.apiweb.redsocial;

import com.apiweb.redsocial.application.service.PostService;
import com.apiweb.redsocial.application.service.UserService;
import com.apiweb.redsocial.domain.model.Post;
import com.apiweb.redsocial.domain.model.User;
import com.apiweb.redsocial.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase de prueba para la aplicación.
 */

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void testCreateUser() {
        userService.createUser("Alfonso");
        User user = userRepository.findByName("Alfonso").orElse(null);
        System.out.println("Usuario creado: " + (user != null ? user.getName() : "null"));
        assertEquals("Alfonso", user.getName());
    }

    @Test
    void testFollowUser() {
        userService.createUser("Alicia");
        userService.createUser("Ivan");
        userService.followUser("Alicia", "Ivan");

        User user = userRepository.findByName("Alicia").orElse(null);
        User followee = userRepository.findByName("Ivan").orElse(null);

        System.out.println("Usuario Alicia sigue a: " +
                (user != null ? user.getFollowing().stream().map(User::getName).collect(Collectors.toList()) : "null"));
        assertEquals(1, user.getFollowing().size());
        assertEquals(followee, user.getFollowing().get(0));
    }

    @Test
    void testCreatePost() {
        userService.createUser("Ivan");
        postService.createPost("Ivan", "Hoy puede ser un gran día");

        User user = userRepository.findByName("Ivan").orElse(null);
        Post post = user.getPosts().get(0);

        System.out.println("Publicación creada: " + (post != null ? post.getContent() + " @" + user.getName() + " @" + post.getTimestamp() : "null"));
        assertEquals(1, user.getPosts().size());
        assertEquals("Hoy puede ser un gran día", post.getContent());
    }

    @Test
    void testGetWall() {
        userService.createUser("Alicia");
        userService.createUser("Ivan");

        postService.createPost("Ivan", "Hoy puede ser un gran día");
        userService.followUser("Alicia", "Ivan");

        var wall = userService.getWall("Alicia");

        System.out.println("Muro de Alicia: " + wall);
        assertEquals(1, wall.size());
        String expectedPost = String.format("Hoy puede ser un gran día @Ivan @%s", LocalDateTime.now().toLocalDate());
        assertEquals(expectedPost, wall.get(0));
    }

    @Test
    void testGetAllUsers() {
        userService.createUser("Alicia");
        userService.createUser("Ivan");

        var users = userService.getAllUsers();

        System.out.println("Usuarios: " + users.stream().map(User::getName).collect(Collectors.toList()));
        assertEquals(2, users.size());
    }
}
