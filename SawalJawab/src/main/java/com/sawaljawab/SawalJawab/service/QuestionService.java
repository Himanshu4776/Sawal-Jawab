package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.CategoryDto;
import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.Repositories.QuestionRepository;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.Category;
import com.sawaljawab.SawalJawab.entities.Questions;
import com.sawaljawab.SawalJawab.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilService utils;

    @Transactional
    public QuestionsDto saveQuestion(QuestionsDto questionsDto, String username) {
        User foundUser = userRepository.findByUserName(username);
        if (foundUser != null && utils.checkQuestionCompliance(questionsDto)) {
            Questions question = modelMapper.map(questionsDto, Questions.class);
            if (question.getCategory() != null) {
                Category foundCategory = categoryService.getCategoryFromName(question.getCategory().getCategoryName());
                // Set the relationship
                if(foundCategory == null) {
                    log.info("category not found with category name: {}", question.getCategory().getCategoryName());
                    // make a category
                    log.debug("creating a new category name: {}", question.getCategory().getCategoryName());
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setCategoryName(question.getCategory().getCategoryName());
                    Category addedCategory = categoryService.addCategory(categoryDto);
                    question.setCategory(addedCategory);
                } else {
                    question.setCategory(foundCategory);
                }
            }
            question.setUser(foundUser);

            question.setCreatedAt(LocalDateTime.now());
            question.setUpdatedAt(LocalDateTime.now());

            Questions saved = questionRepository.save(question);
            return modelMapper.map(saved, QuestionsDto.class);
        }
        log.warn("No user found with username {}", username);
        return null;
    }

    public boolean saveQuestion(Questions questions) {
        Questions saved = questionRepository.save(questions);
        if (saved != null) {
            return true;
        }
        log.warn("Error while saving question: {}", questions);
        return false;
    }

    @Transactional(readOnly = true)
    public QuestionsDto getQuestionOwner(Long questionId) {

        Optional<Questions> question = questionRepository.findById(questionId);
        return question.map(questions -> modelMapper.map(questions, QuestionsDto.class)).orElse(null);
    }

    public Optional<Questions> getQuestion(Long questionId) {
        return questionRepository.findById(questionId);
    }

    public List<QuestionsDto> getAllQuestions() {
        List<Questions> questionsList = questionRepository.findAll();

        return questionsList.stream()
                .map(q -> modelMapper.map(q, QuestionsDto.class))
                .collect(Collectors.toList());
    }

    public List<QuestionsDto> getUserAllQuestions(String username) {
        User foundUser = userRepository.findByUserName(username);
        if (foundUser != null) {
            return foundUser.getQuestions().stream()
                    .map(q -> modelMapper.map(q, QuestionsDto.class))
                    .toList();
        }
        log.warn("No user found with username {}", username);
        return null;
    }
}
