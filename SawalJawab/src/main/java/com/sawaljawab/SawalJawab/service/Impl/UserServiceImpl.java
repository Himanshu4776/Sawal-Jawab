package com.sawaljawab.SawalJawab.service.Impl;

import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.User;
import com.sawaljawab.SawalJawab.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto findUser(String userName) {
        User foundUser = userRepository.findByUserName(userName);
        if (foundUser != null) {
            UserDto userDto = new UserDto();
            userDto.setUserName(foundUser.getUsername());
            userDto.setPassword(foundUser.getPassword());
            userDto.setCreatedAt(foundUser.getCreatedAt());
            userDto.setUpdatedAt(foundUser.getUpdatedAt());
            userDto.setRole(foundUser.getRole());
            userDto.setEmail(foundUser.getEmail());

            return  userDto;
        }
        return null;
    }

}
