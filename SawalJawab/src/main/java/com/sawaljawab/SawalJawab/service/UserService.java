package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDto findUser(String userName);
}
