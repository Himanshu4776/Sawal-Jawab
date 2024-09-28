package com.sawaljawab.SawalJawab.Repositories;

import com.sawaljawab.SawalJawab.entities.Answer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerRepository extends MongoRepository<Answer, ObjectId> {
}
