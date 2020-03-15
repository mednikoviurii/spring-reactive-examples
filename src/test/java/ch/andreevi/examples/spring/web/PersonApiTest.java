package ch.andreevi.examples.spring.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

import static org.assertj.core.api.Assertions.*;

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
        client.get().uri("/person/{id}", id).exchange()
            .expectBody(Person.class)
            .value(p -> assertThat(p).isNotNull().hasNoNullFieldsOrProperties());
    }

    @Test
    void createTest(){
        // asserting body
        Person person = new Person("id", "John", "Doe", 25);
        Mono<Person> source = Mono.just(person);
        when(service.create(any(Person.class))).thenReturn(source);
        client.post()
            .uri("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(person))
            .exchange().expectBody(Person.class).value(p -> assertThat(p)
                .hasFieldOrPropertyWithValue("firstName", person.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", person.getLastName())
                .hasFieldOrPropertyWithValue("id", person.getId())
                .hasFieldOrPropertyWithValue("age", person.getAge()));
    }

    @Test
    void deleteTest(){
        // basic assertions
        when(service.remove(anyString())).thenReturn(Mono.empty());
        client.delete().uri("/person/{id}", "id").exchange().expectStatus().isOk();
    }
}