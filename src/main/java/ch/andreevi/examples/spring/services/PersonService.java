package ch.andreevi.examples.spring.services;

import org.springframework.stereotype.Service;

import ch.andreevi.examples.spring.models.Person;
import reactor.core.publisher.Mono;

@Service
public interface PersonService {

    Mono<Person> create (Person person);

    Mono<Void> remove (String id);

    Mono<Person> findById (String id);

    Mono<Void> update (Person person);
}