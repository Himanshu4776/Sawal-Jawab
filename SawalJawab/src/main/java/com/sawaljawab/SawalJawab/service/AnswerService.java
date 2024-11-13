package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.AnswerRepository;
import com.sawaljawab.SawalJawab.entities.Answer;
import com.sawaljawab.SawalJawab.entities.Questions;
import com.sawaljawab.SawalJawab.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Answer saveAnswer(Answer answer, Long questionId, String username) {
        User foundUser = userService.getOlderUser(username);
        if (foundUser != null) {
            Optional<Questions> question = questionService.getQuestion(questionId);
            if (question.isPresent()) {
                answer.setCreatedAt(LocalDateTime.now());
                answer.setUpdatedAt(LocalDateTime.now());
                answer.setUser(foundUser);
                answer.setQuestion(question.get());

                Answer savedAnswer = answerRepository.save(answer);
                return savedAnswer;
            }
            log.warn("No question present with question_id {}", questionId);
        }
        log.warn("No user found with username {}", username);
        return null;
    }

    public Optional<Answer> getAnswer(Long answerId) {
        return answerRepository.findById(answerId);
    }
}
