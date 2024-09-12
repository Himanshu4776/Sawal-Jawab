package com.sawaljawab.SawalJawab.Repositories;

import com.sawaljawab.SawalJawab.entities.Questions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Questions, ObjectId> {
}
