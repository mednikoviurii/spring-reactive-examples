package ch.andreevi.examples.spring.models;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value @AllArgsConstructor
public class Role {

    String permission;
    String level;

}