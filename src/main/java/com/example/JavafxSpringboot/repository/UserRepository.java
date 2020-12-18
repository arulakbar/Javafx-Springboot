package com.example.JavafxSpringboot.repository;

import com.example.JavafxSpringboot.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByName(String name);
    User findByName(String name);

}
