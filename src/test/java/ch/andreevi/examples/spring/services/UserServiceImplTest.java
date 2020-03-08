package ch.andreevi.examples.spring.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.andreevi.examples.spring.models.User;
import ch.andreevi.examples.spring.repositories.UserRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks private UserServiceImpl service;
    @Mock private UserRepository repository;

    @Test
    void createUserTest() {
        User entity = User.dummy();
        Mono<User> source = Mono.just(entity);
        when(repository.save(any(User.class))).thenReturn(source);
        StepVerifier.create(service.createUser(entity))
            .assertNext(user -> assertThat(user)
                        .isNotNull()
                        .hasFieldOrPropertyWithValue("email", "email")
                        .hasFieldOrPropertyWithValue("userId", "userId")
                        .hasFieldOrPropertyWithValue("password", "password")
                        .hasNoNullFieldsOrPropertiesExcept("roles"))
            .verifyComplete();
    }

    @Test
    void findUserByIdSuccessTest(){
        User entity = User.dummy();
        String userId = entity.getUserId();
        Mono<User> source = Mono.just(entity);
        when(repository.findById(userId)).thenReturn(source);
        StepVerifier.create(service.findOne(userId))
            .assertNext(user -> assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("email", "email")
                .hasFieldOrPropertyWithValue("userId", "userId")
                .hasFieldOrPropertyWithValue("password", "password")
                .hasNoNullFieldsOrPropertiesExcept("roles")).verifyComplete();
    }

    @Test
    void findUserByIdFailureTest(){
        String userId = "no-such-user-id";
        Mono<User> source = Mono.empty();
        when(repository.findById(userId)).thenReturn(source);
        StepVerifier.create(service.findOne(userId)).verifyComplete();
    }

    @Test
    void changePasswordTest() {
        String userId = "userId";
        String password = "password";
        Mono<User> source = Mono.just(User.dummy());
        when(repository.changePassword(userId, password)).thenReturn(source);
        StepVerifier.create(service.changePassword(userId, password))
            .assertNext(user -> assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("email", "email")
                .hasFieldOrPropertyWithValue("userId", "userId")
                .hasFieldOrPropertyWithValue("password", "password")
                .hasNoNullFieldsOrPropertiesExcept("roles")).verifyComplete();
    }
}