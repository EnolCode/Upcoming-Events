package com.Upcoming.Events.demo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD

import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.repositories.EventRepository;
import com.Upcoming.Events.demo.services.EventServiceImpl;
=======
import com.upcoming.events.demo.models.Event;
import com.upcoming.events.demo.repositories.EventRepository;
>>>>>>> origin/develop

import com.upcoming.events.demo.models.Event;
import com.upcoming.events.demo.repositories.EventRepository;
import com.upcoming.events.demo.services.EventServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private List<Event> eventList;


    @BeforeEach
    public void setUp() {
        eventList = new ArrayList<>();
        eventList.add(new Event("Evento 1",null, 10, "Evento de prueba 1", 1));
        eventList.add(new Event("Evento 2", null, 20, "Evento de prueba 2", 2));
        eventList.add(new Event("Evento 3", null, 30, "Evento de prueba 3", 3));
    }

    @Test
    public void testFindAll() {
        when(eventRepository.findAll()).thenReturn(eventList);

        List<Event> result = eventService.findAll();

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getTitle()).isEqualTo("Evento 1");
        assertThat(result.get(1).getTitle()).isEqualTo("Evento 2");
        assertThat(result.get(2).getTitle()).isEqualTo("Evento 3");
    }

    @Test
    public void testFindAllPageable() {
        Page<Event> eventPage = new PageImpl<>(eventList);
        Pageable pageable = Pageable.unpaged();

        when(eventRepository.findAll(pageable)).thenReturn(eventPage);

        Page<Event> result = eventService.findAll(pageable);

        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Evento 1");
        assertThat(result.getContent().get(1).getTitle()).isEqualTo("Evento 2");
        assertThat(result.getContent().get(2).getTitle()).isEqualTo("Evento 3");
    }

    @Test
    public void testSave() {
        Event event = new Event("Evento 4", null, 40, "Evento de prueba 4", 4);

        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event result = eventService.save(event);

        assertThat(result.getId()).isEqualTo(0L);
        assertThat(result.getTitle()).isEqualTo("Evento 4");
        // assertThat(result.getDate_hour()).isEqualTo("2023-04-01");
        assertThat(result.getMax_participants()).isEqualTo(40);
        assertThat(result.getDescription()).isEqualTo("Evento de prueba 4");
        assertThat(result.getActual_participants()).isEqualTo(4);
    }

    // @Test
    // public void testDeleteEvent() throws Exception {
    //     doNothing().when(eventRepository).deleteById(event.getId());
    //     when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));

    //     eventService.deleteById(event.getId());

    //     Optional<Event> deletedEvent = eventService.findById(event.getId());
    //     assertThat(deletedEvent).isEmpty();

    // }
}
