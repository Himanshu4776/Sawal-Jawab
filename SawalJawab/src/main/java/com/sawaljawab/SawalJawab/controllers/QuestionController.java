package com.sawaljawab.SawalJawab.controllers;

import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/save/{username}")
    public ResponseEntity<QuestionsDto> createQuestion(@RequestBody QuestionsDto questionsDto, @PathVariable String username){
        QuestionsDto saved = questionService.saveQuestion(questionsDto,username);
        return new ResponseEntity<QuestionsDto>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<QuestionsDto> getQuestion(@PathVariable Long id) {
        QuestionsDto question = questionService.getQuestionOwner(id);
        if (question != null) {
            return new ResponseEntity<QuestionsDto>(question, HttpStatus.FOUND);
        }
        Logger.getLogger("No Question found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<QuestionsDto>> getAllQuestions() {
        List<QuestionsDto> allQuestions = questionService.getAllQuestions();
        if (!allQuestions.isEmpty()) {
            return new ResponseEntity<List<QuestionsDto>>(allQuestions, HttpStatus.FOUND);
        }
        Logger.getLogger("No Question found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{filterString}")
    public ResponseEntity<List<QuestionsDto>> getSearchedQuestion(@PathVariable String filterString) {
        List<QuestionsDto> searchedQuestion = questionService.getSearchedQuestion(filterString);
        return new ResponseEntity<>(searchedQuestion, HttpStatus.OK);
    }
}
