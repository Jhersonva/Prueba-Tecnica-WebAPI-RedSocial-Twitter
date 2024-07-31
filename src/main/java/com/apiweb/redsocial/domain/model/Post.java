package com.apiweb.redsocial.domain.model;

import java.time.LocalDateTime;

/**
 * Clase de modelo de dominio que representa una publicación realizada por un usuario.
 */
public class Post {
    private String content;
    private LocalDateTime timestamp;

    public Post(String content, LocalDateTime timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
