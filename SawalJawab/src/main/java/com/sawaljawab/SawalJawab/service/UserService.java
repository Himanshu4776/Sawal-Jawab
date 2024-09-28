package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    public void saveOlderUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User editUser(User userToEdit, String userName) {
        UserDto userFound = findUser(userName);
        if (userFound != null) {
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
}
