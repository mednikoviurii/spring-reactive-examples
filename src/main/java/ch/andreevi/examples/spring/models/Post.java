package ch.andreevi.examples.spring.models;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value @AllArgsConstructor
public class Post {
    String userId;
    String id;
    String title;
    String body;
}