package com.Upcoming.Events.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Upcoming.Events.demo.models.Event;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTest {

    @Autowired
    EventRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById(){
       Event event =  repository.findById(1L).orElseThrow();
       assertThat(event.getId()).isEqualTo(1L);
       assertThat(event.getTitle()).isEqualTo("Soy el concierto 1");
    }

    @Test
    public void findByTitle(){
        Event event = repository.findByTitle("Soy el concierto 4").orElseThrow();
        assertThat(event.getId()).isEqualTo(4L);
        assertThat(event.getTitle()).isEqualTo("Soy el concierto 4");
    }
     
    @Test
    public void saveFilm(){
        Event event = new Event();
        event.setTitle("Film3");
        event.setActual_participants(8);
        repository.save(event);
        assertThat(event.getId()).isNotNull();
    }

    @Test
    public void deleteFilm(){
        Event event = repository.findById(1L).orElseThrow();
        repository.delete(event);
        assertThat(repository.findById(1L)).isEmpty();
    }

    @Test
    public void updateFilm(){
        Event event = repository.findById(1L).orElseThrow();
        event.setTitle("Soy el concierto editado");
        repository.save(event);
        assertThat(event.getTitle()).isEqualTo("Soy el concierto editado");
    }
}
