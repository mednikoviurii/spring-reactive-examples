package ch.andreevi.examples.spring.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ch.andreevi.examples.spring.models.Person;
import ch.andreevi.examples.spring.services.PersonService;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

    private final MediaType json = MediaType.APPLICATION_JSON;
    private final PersonService service;

    public PersonHandler(PersonService service) {
        this.service = service;
    }

    public Mono<ServerResponse> createPerson (ServerRequest request){
        Mono<Person> payload = request.bodyToMono(Person.class);
        return ServerResponse.ok().contentType(json).body(payload.flatMap(service::create), Person.class);
    }
    
    public Mono<ServerResponse> remove (ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(json).body(service.remove(id), Void.class);
    }

    public Mono<ServerResponse> update (ServerRequest request){
        Mono<Person> payload = request.bodyToMono(Person.class);
        return ServerResponse.ok().contentType(json).body(payload.flatMap(service::update), Void.class);
    }

    public Mono<ServerResponse> findById (ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(json).body(service.findById(id), Person.class);
    }
}