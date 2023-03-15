package com.Upcoming.Events.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Upcoming.Events.demo.models.Event;

public interface EventRepository extends JpaRepository <Event, Long> {
    
}
