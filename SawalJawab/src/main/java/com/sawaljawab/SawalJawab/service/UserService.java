package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

    public User saveUser(User userToSave) {
        if(userToSave.getRole() == null) {
            userToSave.setRole("user");
        }
        userToSave.setCreatedAt(LocalDateTime.now());
        userToSave.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(userToSave);
        return savedUser;
    }
}
