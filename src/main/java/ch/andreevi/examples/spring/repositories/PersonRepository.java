package ch.andreevi.examples.spring.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ch.andreevi.examples.spring.models.Person;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, String> {}