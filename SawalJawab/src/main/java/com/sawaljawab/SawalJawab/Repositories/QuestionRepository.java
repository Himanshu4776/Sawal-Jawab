package com.sawaljawab.SawalJawab.Repositories;

import com.sawaljawab.SawalJawab.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions, Long> {
    @Query(value = "SELECT * FROM product WHERE MATCH(title, content, tag) AGAINST(:keyword IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    List<Questions> searchProducts(@Param("keyword") String keyword);
}
