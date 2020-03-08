package ch.andreevi.examples.spring.services;

import org.springframework.stereotype.Component;

import ch.andreevi.examples.spring.models.User;
import ch.andreevi.examples.spring.repositories.UserRepository;
import reactor.core.publisher.Mono;

@Component("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> createUser(User user) {
        return this.repository.save(user);
    }

    @Override
    public Mono<Void> removeUser(String userId) {
        return this.repository.deleteById(userId);
    }

    @Override
    public Mono<User> findOne(String userId) {
        return this.repository.findById(userId);
    }

    @Override
    public Mono<User> changePassword(String userId, String password) {
        return this.repository.changePassword(userId, password);
    }

}