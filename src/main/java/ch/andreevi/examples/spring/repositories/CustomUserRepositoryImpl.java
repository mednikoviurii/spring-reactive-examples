package ch.andreevi.examples.spring.repositories;

import com.mongodb.BasicDBObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import ch.andreevi.examples.spring.models.Role;
import ch.andreevi.examples.spring.models.User;
import reactor.core.publisher.Mono;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomUserRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<User> changePassword(String userId, String newPassword) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update().set("password", newPassword);
        return mongoTemplate.findAndModify(query, update, User.class);
    }

    @Override
    public Mono<User> addNewRole(String userId, Role role) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update().addToSet("roles", role);
        // Update updateWithPush = new Update().push("roles", role);
        return mongoTemplate.findAndModify(query, update, User.class);
    }

    @Override
    public Mono<User> removeRole(String userId, String permission) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update().pull("roles",
             new BasicDBObject("permission", permission));
        return mongoTemplate.findAndModify(query, update, User.class);
    }

    @Override
    public Mono<Boolean> hasPermission(String userId, String permission) {
        Query query = new Query(Criteria.where("userId").is(userId))
            .addCriteria(Criteria.where("roles.permission").is(permission));
        return mongoTemplate.exists(query, User.class);
    }

}