package com.example.dodgema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dodgema.model.Hello;

public interface HelloDao extends JpaRepository <Hello, Integer> {

}