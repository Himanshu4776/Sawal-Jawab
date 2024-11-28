package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.AnswerDto;
import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.Security.JWTService;
import com.sawaljawab.SawalJawab.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    
    public UserService(AuthenticationManager authManager, 
                      UserRepository userRepository,
                      JWTService jwtService) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UtilService utils;
    @Autowired
    private PasswordEncoder encoder;

    public UserDto findUser(String userName) {
        User foundUser = userRepository.findByUserName(userName);
        if (foundUser != null) {
            return modelMapper.map(foundUser, UserDto.class);
        }
        log.warn("No user found with username {}", userName);
        return null;
    }

    public User getOlderUser(String username) {
        return userRepository.findByUserName(username);
    }

    public User saveUser(User userToSave) {
        if(userToSave.getRole() == null) {
            userToSave.setRole("user");
        }
        userToSave.setPassword(encoder.encode(userToSave.getPassword()));
        userToSave.setCreatedAt(LocalDateTime.now());
        userToSave.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(userToSave);
        return savedUser;
    }

    public void saveOlderUser(User user) {
        try {
            userRepository.save(user);  // This is where the exception likely occurs
        } catch (Exception e) {
            log.error("Exception occurred during save of user {} with error: {}", user, e.getMessage(), e);
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
        log.warn("No user found with username {}", userName);
        return null;
    }

    public Boolean deleteUser(String userName) {
        UserDto userDto = findUser(userName);
        if (userDto != null) {
            userRepository.deleteByUserName(userName);
            return true;
        }
        log.warn("No user found with username {}", userName);
        return false;
    }

    public List<AnswerDto> getAnswers(String userName) {
        User user = getOlderUser(userName);
        if (user != null) {
            return user.getAnswers().stream()
                    .map(answer -> modelMapper.map(answer, AnswerDto.class))
                    .collect(Collectors.toList());
        }
        log.warn("No user found with username {}", userName);
        return null;
    }

    public List<QuestionsDto> getQuestions(String userName) {
        User user = getOlderUser(userName);
        if (user != null) {
            return user.getQuestions().stream()
                    .map(questions -> modelMapper.map(questions, QuestionsDto.class))
                    .collect(Collectors.toList());
        }
        log.warn("No user found with username {}", userName);
        return null;
    }

    public String verify(UserDto userDto) {

        Authentication authentication=
                authManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(),userDto.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(userDto.getUserName());
        return "fail";
    }
}
