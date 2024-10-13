package com.sawaljawab.SawalJawab.Repositories;

import com.sawaljawab.SawalJawab.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions, Long> {
}
