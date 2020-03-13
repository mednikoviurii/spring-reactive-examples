package ch.andreevi.examples.spring.models;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor @Value
public class Person {

    String id;
    String firstName;
    String lastName;
    int age;

}