package com.cts.srbdd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.srbdd.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByName(String name);
    
}
