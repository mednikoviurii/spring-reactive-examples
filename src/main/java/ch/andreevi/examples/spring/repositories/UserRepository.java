package ch.andreevi.examples.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import ch.andreevi.examples.spring.models.User;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>, CustomUserRepository{
    // derived query method
    Mono<User> findByEmail (String email);
}