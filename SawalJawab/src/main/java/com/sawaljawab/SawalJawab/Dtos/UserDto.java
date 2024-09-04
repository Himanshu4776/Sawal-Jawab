package com.sawaljawab.SawalJawab.Dtos;

import com.sawaljawab.SawalJawab.constants.UserConstants;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private String userName;
    private String password;
    private String email;
    private UserConstants.UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
