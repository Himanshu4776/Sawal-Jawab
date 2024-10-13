package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
    public boolean checkUserCompliance(User userDto) {
        if (userDto.getEmail().isEmpty() || userDto.getEmail().isBlank() || !userDto.getEmail().contains("@")) {
            return false;
        }
        if (userDto.getPassword().isEmpty() || userDto.getPassword().isBlank()) {
            return false;
        }
        if (userDto.getUserName().isBlank() || userDto.getUserName().isEmpty()) {
            return false;
        }
        return  true;
    }

    public boolean checkQuestionCompliance(QuestionsDto questionsDto) {
        if (questionsDto.getTitle().isEmpty() || questionsDto.getTitle().isBlank()) {
            return false;
        }
        if (questionsDto.getContent().isEmpty() || questionsDto.getContent().isBlank()) {
            return false;
        }
//        if (questionsDto.getCategoryName() != null && questionsDto.getCategoryName().isBlank()
//                && questionsDto.getCategoryName().contains("@/,.123456789][()_-!") || !questionsDto.getCategoryName().isEmpty()) {
//            return false;
//        }
        return true;
    }
}
