package com.Upcoming.Events.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Upcoming.Events.demo.models.User;

public interface UserRepository extends JpaRepository <User, Long> {
    
}
