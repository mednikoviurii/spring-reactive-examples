package ch.andreevi.examples.spring.repositories;

import ch.andreevi.examples.spring.models.Role;
import ch.andreevi.examples.spring.models.User;
import reactor.core.publisher.Mono;

public interface CustomUserRepository {

    Mono<User> changePassword (String userId, String newPassword);

    Mono<User> addNewRole (String userId, Role role);

    Mono<User> removeRole (String userId, String permission);

    Mono<Boolean> hasPermission (String userId, String permission);
}