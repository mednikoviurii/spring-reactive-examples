package ch.andreevi.examples.spring.clients;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.andreevi.examples.spring.models.Post;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;

class PostClientImplTest {

    private static PostClientImpl client;

    @BeforeAll
    static void setup(){
        client = new PostClientImpl();
    }

    @Test
    void findAllTest() {
        StepVerifier.create(client.findAll()).expectNextCount(100);
    }

    @Test
    void findOneTest(){
        String id = "20";
        String title = "doloribus ad provident suscipit at";
        StepVerifier.create(client.findById(id)).assertNext(post -> assertThat(post)
            .isNotNull().hasFieldOrPropertyWithValue("title", title)).verifyComplete();
    }

    @Test
    void createTest(){
        Post post = new Post("userId", "id", "title", "body");
        // NB JsonPlaceholder does not actually save posts, just return results
        StepVerifier.create(client.create(post)).expectNext(post);
    }

    @Test
    void removeTest(){
        String id = "10";
        // NB JsonPlaceholder does not actually remove data
        StepVerifier.create(client.remove(id)).expectComplete();
    }
}