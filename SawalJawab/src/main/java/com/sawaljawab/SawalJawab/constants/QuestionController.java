package com.sawaljawab.SawalJawab.constants;

import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.entities.Questions;
import com.sawaljawab.SawalJawab.service.QuestionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/save/{username}")
    public QuestionsDto createQuestion(@RequestBody QuestionsDto questionsDto, @PathVariable String username){
        QuestionsDto saved=questionService.saveQuestion(questionsDto,username);
        return saved;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<QuestionsDto> getQuestions(@PathVariable ObjectId id) {
        QuestionsDto question = questionService.getQuestionOwner(id);
        if (question != null) {
            return new ResponseEntity<QuestionsDto>(question, HttpStatus.FOUND);
        }
        Logger.getLogger("No Question found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
