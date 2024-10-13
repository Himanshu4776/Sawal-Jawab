package com.sawaljawab.SawalJawab.controllers;

import com.sawaljawab.SawalJawab.entities.Answer;
import com.sawaljawab.SawalJawab.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/userName/{userName}/questionId/{questionId}")
    public ResponseEntity<Answer> saveQuestionAnswers(@PathVariable String userName, @PathVariable Long questionId,
                                              @RequestBody Answer answer) {
        Answer savedAnswer = answerService.saveAnswer(answer, questionId, userName);
        if (savedAnswer != null) {
            return new ResponseEntity<Answer>(savedAnswer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/userName/{userName}/questionId/{questionId}/answerId/{answerId}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Long answerId) {
        Optional<Answer> answer = answerService.getAnswer(answerId);
        if (answer.isPresent()) {
            return new ResponseEntity<Answer>(answer.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
