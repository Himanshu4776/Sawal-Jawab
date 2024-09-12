package com.sawaljawab.SawalJawab.constants;

import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
