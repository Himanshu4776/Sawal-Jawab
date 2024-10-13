package com.sawaljawab.SawalJawab.Repositories;

import com.sawaljawab.SawalJawab.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    void deleteByUserName(String username);
}
