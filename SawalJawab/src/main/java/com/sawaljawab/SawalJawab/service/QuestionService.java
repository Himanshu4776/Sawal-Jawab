package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.QuestionRepository;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.Questions;
import com.sawaljawab.SawalJawab.entities.User;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired UserService userService;

    @Transactional
    public QuestionsDto saveQuestion(QuestionsDto questionsDto, String username) {
        User foundUser = userRepository.findByUserName(username);
        if (foundUser!=null){
            Questions question=modelMapper.map(questionsDto, Questions.class);
            question.setUserName(username);
            question.setCreatedAt(LocalDateTime.now());
            question.setUpdatedAt(LocalDateTime.now());
            Questions savedQuestion = questionRepository.save(question);
            if(foundUser.getQuestions() != null) {
                foundUser.getQuestions().add(savedQuestion);
            } else {
                ArrayList<Questions> questionsList = new ArrayList<>();
                questionsList.add(savedQuestion);
                foundUser.setQuestions(questionsList);
            }
//            userService.editUser(foundUser, username);
            userRepository.save(foundUser);
            return modelMapper.map(savedQuestion, QuestionsDto.class);
        }
        return null;
    }

    public QuestionsDto getQuestionOwner(ObjectId questionId) {
        Optional<Questions> question = questionRepository.findById(questionId);
        return question.map(questions -> modelMapper.map(questions, QuestionsDto.class)).orElse(null);
    }
}
