package ch.andreevi.examples.spring.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import ch.andreevi.examples.spring.models.Person;
import ch.andreevi.examples.spring.services.PersonService;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebRouter.class, PersonHandler.class})
@WebFluxTest
class PersonApiTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private PersonService service;

    @Test
    void findByIdTest(){
        Person person = new Person("id", "John", "Doe", 25);
        String id = "id";
        Mono<Person> source = Mono.just(person);
        when(service.findById(id)).thenReturn(source);
        client.get().uri("/person/{id}", id).exchange().expectStatus().isOk();
    }

    @Test
    void createTest(){
        Person person = new Person("id", "John", "Doe", 25);
        Mono<Person> source = Mono.just(person);
        when(service.create(any(Person.class))).thenReturn(source);
        client.post()
            .uri("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(person))
            .exchange().expectStatus().isOk();
    }
}