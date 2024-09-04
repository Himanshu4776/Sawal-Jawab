package com.sawaljawab.SawalJawab.Repositories;

import com.sawaljawab.SawalJawab.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
