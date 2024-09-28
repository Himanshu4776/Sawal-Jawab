package com.sawaljawab.SawalJawab.controllers;

import com.sawaljawab.SawalJawab.entities.Answer;
import com.sawaljawab.SawalJawab.service.AnswerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/userName/{userName}/questionId/{questionId}")
    public ResponseEntity<Answer> saveQuestionAnswers(@PathVariable String userName, @PathVariable ObjectId questionId,
                                              @RequestBody Answer answer) {
        Answer savedAnswer = answerService.saveAnswer(answer, questionId, userName);
        if (savedAnswer != null) {
            return new ResponseEntity<Answer>(savedAnswer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
