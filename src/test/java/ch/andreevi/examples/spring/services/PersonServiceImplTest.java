package ch.andreevi.examples.spring.services;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.andreevi.examples.spring.models.Person;
import ch.andreevi.examples.spring.repositories.PersonRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    
    @InjectMocks private PersonServiceImpl service;
    @Mock private PersonRepository repository;

    @Test
    void createPersonTest(){
        Person data = new Person("id", "John", "Doe", 28);
        Mono<Person> source = Mono.just(data);
        when(repository.save(any(Person.class))).thenReturn(source);
        StepVerifier.create(service.create(data)).assertNext(person -> 
            assertThat(person).hasNoNullFieldsOrProperties()).verifyComplete();
    }

    @Test
    void findByIdTest(){
        String id = "id";
        Person data = new Person("id", "John", "Doe", 28);
        Mono<Person> source = Mono.just(data);
        when(repository.findById(id)).thenReturn(source);
        StepVerifier.create(service.findById(id)).assertNext(person -> 
            assertThat(person).hasNoNullFieldsOrProperties()).verifyComplete();
    }

    @Test
    void removeTest() {
        String id = "id";
        when(repository.deleteById(anyString())).thenReturn(Mono.empty());
        StepVerifier.create(service.remove(id)).verifyComplete();
    }
}