package com.sawaljawab.SawalJawab.controllers;

import com.sawaljawab.SawalJawab.Dtos.AnswerDto;
import com.sawaljawab.SawalJawab.Dtos.AuthRequest;
import com.sawaljawab.SawalJawab.Dtos.QuestionsDto;
import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Security.JWTService;
import com.sawaljawab.SawalJawab.entities.Answer;
import com.sawaljawab.SawalJawab.entities.User;
import com.sawaljawab.SawalJawab.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userName) {
        UserDto user = userService.findUser(userName);
        if (user != null) {
            return new ResponseEntity<UserDto>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUserEntry(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            UserDto userDto = modelMapper.map(savedUser, UserDto.class);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<UserDto> editUserEntry(@RequestBody User user, @PathVariable String userName) {
        User editedUser = userService.editUser(user, userName);
        if (editedUser != null) {
            UserDto userDto = modelMapper.map(editedUser, UserDto.class);
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Boolean> deleteUserEntry(@PathVariable String userName) {
        Boolean deleted = userService.deleteUser(userName);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userName}/answers")
    public ResponseEntity<List<AnswerDto>> getUserAnswers(@PathVariable String userName) {
        List<AnswerDto> answers = userService.getAnswers(userName);
        if (!answers.isEmpty()) {
            return new ResponseEntity<>(answers, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{userName}/questions")
    public ResponseEntity<List<QuestionsDto>> getUserQuestions(@PathVariable String userName) {
        List<QuestionsDto> questions = userService.getQuestions(userName);
        if (!questions.isEmpty()) {
            return new ResponseEntity<>(questions, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto){
        return userService.verify(userDto);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
