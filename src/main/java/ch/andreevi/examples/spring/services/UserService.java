package ch.andreevi.examples.spring.services;

import org.springframework.stereotype.Service;

import ch.andreevi.examples.spring.models.User;
import reactor.core.publisher.Mono;

@Service
public interface UserService {

    Mono<User> createUser(User user);

    Mono<Void> removeUser(String userId);

    Mono<User> findOne (String userId);

    Mono<User> changePassword (String userId, String password);
}