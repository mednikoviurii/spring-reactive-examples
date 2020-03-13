package ch.andreevi.examples.spring.services;

import org.springframework.stereotype.Component;

import ch.andreevi.examples.spring.models.Person;
import ch.andreevi.examples.spring.repositories.PersonRepository;
import reactor.core.publisher.Mono;

@Component("PersonService")
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Person> create(Person person) {
        return this.repository.save(person);
    }

    @Override
    public Mono<Void> remove(String id) {
        return this.repository.deleteById(id);
    }

    @Override
    public Mono<Person> findById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Mono<Void> update(Person person) {
        return this.repository.save(person).then();
    }

}