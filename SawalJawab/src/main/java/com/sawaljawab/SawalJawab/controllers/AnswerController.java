package com.sawaljawab.SawalJawab.controllers;

import com.sawaljawab.SawalJawab.Dtos.AnswerDto;
import com.sawaljawab.SawalJawab.entities.Answer;
import com.sawaljawab.SawalJawab.service.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/questionId/{questionId}")
    public ResponseEntity<AnswerDto> saveQuestionAnswers(@PathVariable Long questionId,
                                              @RequestBody Answer answer) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Answer savedAnswer = answerService.saveAnswer(answer, questionId, userName);
        if (savedAnswer != null) {
            AnswerDto answerDto = modelMapper.map(savedAnswer, AnswerDto.class);
            return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/questionId/{questionId}/answerId/{answerId}")
    public ResponseEntity<AnswerDto> getAnswer(@PathVariable Long answerId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Answer> answer = answerService.getAnswer(answerId);
        if (answer.isPresent()) {
            AnswerDto answerDto = modelMapper.map(answer.get(), AnswerDto.class);
            return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
