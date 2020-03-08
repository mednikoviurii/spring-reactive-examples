package ch.andreevi.examples.spring.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value @AllArgsConstructor
@Document(collection = "users")
public class User {

    @NonFinal @Id String userId;
    String email;
    String password;
    List<Role> roles;

    public static User dummy() {
        return new User("userId", "email", "password", null);
    }
}