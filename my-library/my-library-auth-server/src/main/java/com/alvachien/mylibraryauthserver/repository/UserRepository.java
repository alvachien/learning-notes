package com.alvachien.mylibraryauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvachien.mylibraryauthserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
}
