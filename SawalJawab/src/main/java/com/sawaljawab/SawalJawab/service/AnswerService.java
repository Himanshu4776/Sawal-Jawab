package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.AnswerRepository;
import com.sawaljawab.SawalJawab.entities.Answer;
import com.sawaljawab.SawalJawab.entities.Questions;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @Transactional
    public Answer saveAnswer(Answer answer, ObjectId questionId, String username) {
        UserDto foundUser = userService.findUser(username);
        if (foundUser != null) {
            Optional<Questions> question = questionService.getQuestion(questionId);
            if (question.isPresent()) {
                answer.setCreatedAt(LocalDateTime.now());
                answer.setUpdatedAt(LocalDateTime.now());
                answer.setUserName(username);
                answer.setQuestionId(questionId);
                Answer savedAnswer = answerRepository.save(answer);
                // save in upper entities like Question and User:
                if (question.get().getAnswer() != null) {
                    question.get().getAnswer().add(answer);
                } else {
                    List<Answer> answers = new ArrayList<>();
                    answers.add(savedAnswer);
                    question.get().setAnswer(answers);
                }
                questionService.saveQuestion(question.get());
                return savedAnswer;
            }
        }
        return null;
    }
}
