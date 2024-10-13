package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.AnswerDto;
import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.Answer;
import com.sawaljawab.SawalJawab.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UtilService utils;

    public UserDto findUser(String userName) {
        User foundUser = userRepository.findByUserName(userName);
        if (foundUser != null) {
            UserDto userDto = new UserDto();
            userDto.setUserName(foundUser.getUserName());
            userDto.setPassword(foundUser.getPassword());
            userDto.setCreatedAt(foundUser.getCreatedAt());
            userDto.setUpdatedAt(foundUser.getUpdatedAt());
            userDto.setRole(foundUser.getRole());
            userDto.setEmail(foundUser.getEmail());

            return  userDto;
        }
        return null;
    }

    public User getOlderUser(String username) {
        return userRepository.findByUserName(username);
    }

    public User saveUser(User userToSave) {
        if(userToSave.getRole() == null) {
            userToSave.setRole("user");
        }
        userToSave.setCreatedAt(LocalDateTime.now());
        userToSave.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(userToSave);
        return savedUser;
    }

//    public void saveOlderUser(User user) {
//        userRepository.save(user);
//    }

    public void saveOlderUser(User user) {
        try {
            userRepository.save(user);  // This is where the exception likely occurs
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception to see what the actual error is
            throw e;  // Rethrow after logging
        }
    }

    @Transactional
    public User editUser(User userToEdit, String userName) {
        UserDto userFound = findUser(userName);
        if (userFound != null && utils.checkUserCompliance(userToEdit)) {
            User mappedUser = modelMapper.map(userFound, User.class);
            mappedUser.setEmail(userToEdit.getEmail());
            mappedUser.setPassword(userToEdit.getPassword());
            mappedUser.setUpdatedAt(LocalDateTime.now());
            if(userToEdit.getRole() != null) {
                mappedUser.setRole(userToEdit.getRole());
            }
            if(userToEdit.getQuestions() != null) {
                mappedUser.setQuestions(userToEdit.getQuestions());
            }
            User user = userRepository.save(mappedUser);
            return user;
        }
        return null;
    }

    public Boolean deleteUser(String userName) {
        UserDto userDto = findUser(userName);
        if (userDto != null) {
            userRepository.deleteByUserName(userName);
            return true;
        }
        return false;
    }

    public List<AnswerDto> getAnswers(String userName) {
        User user = getOlderUser(userName);
        if (user != null) {
            return user.getAnswers().stream()
                    .map(answer -> modelMapper.map(answer, AnswerDto.class))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<QuestionsDto> getQuestions(String userName) {
        User user = getOlderUser(userName);
        if (user != null) {
            return user.getQuestions().stream()
                    .map(questions -> modelMapper.map(questions, QuestionsDto.class))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
