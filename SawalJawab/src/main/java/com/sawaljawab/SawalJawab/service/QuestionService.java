package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.QuestionRepository;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.Questions;
import com.sawaljawab.SawalJawab.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public QuestionsDto saveQuestion(QuestionsDto questionsDto, String username) {
//        UserDto found=userService.findUser(username);
        User foundUser = userRepository.findByUserName(username);

        if (foundUser!=null){
            Questions changed=modelMapper.map(questionsDto, Questions.class);
            changed.setUser(foundUser);
            Questions save = questionRepository.save(changed);
            foundUser.getQuestions().add(changed);
            userRepository.save(foundUser);
            QuestionsDto mappedQuestion = modelMapper.map(save, QuestionsDto.class);
            return mappedQuestion;
        }
        return null;
    }
}
