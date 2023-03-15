package com.upcoming.events.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upcoming.events.demo.models.User;

public interface UserRepository extends JpaRepository <User, Long> {
    
}
