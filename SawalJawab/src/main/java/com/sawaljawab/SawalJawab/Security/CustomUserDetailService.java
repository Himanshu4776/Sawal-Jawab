package com.sawaljawab.SawalJawab.Security;

import com.sawaljawab.SawalJawab.Repositories.UserRepository;
import com.sawaljawab.SawalJawab.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = this.userRepository.findByUserName(username);
        if (user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return user;
    }
}
