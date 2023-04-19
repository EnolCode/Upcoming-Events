package com.Upcoming.Events.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Upcoming.Events.demo.models.Event;


public interface EventRepository extends JpaRepository <Event, Long> {
    Optional<Event> findByTitle(String string);
}
