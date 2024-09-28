package com.sawaljawab.SawalJawab.controllers;

import com.sawaljawab.SawalJawab.Dtos.UserDto;
import com.sawaljawab.SawalJawab.entities.User;
import com.sawaljawab.SawalJawab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userName) {
        UserDto user = userService.findUser(userName);
        if (user != null) {
            return new ResponseEntity<UserDto>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> saveUserEntry(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<User> editUserEntry(@RequestBody User user, @PathVariable String userName) {
        User editedUser = userService.editUser(user, userName);
        if (editedUser != null) {
            return new ResponseEntity<>(editedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Boolean> deleteUserEntry(@PathVariable String userName) {
        Boolean deleted = userService.deleteUser(userName);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}