package com.example.dodgema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dodgema.model.User;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
    User findByIsEnabled(Boolean isEnabled);
    User findByEmail(String email);
    User getUserByEmail(String email);

    Optional<User> findUserByEmailAndPassword(String email, String password);
}