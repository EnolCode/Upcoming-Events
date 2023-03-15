package com.Upcoming.Events.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Upcoming.Events.demo.models.Image;

public interface ImageRepository extends JpaRepository <Image, Long> {
    
}
