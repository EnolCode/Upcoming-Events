package com.Upcoming.Events.demo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;

import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.repositories.EventRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private List<Event> eventList;

    Event event1 = new Event(1L,"Evento 1","22-06-1995",10,"description","style",1);
    Event event2 = new Event(2L,"Evento 2","22-06-1995",10,"description","style",1);
    Event event3 = new Event(3L,"Evento 3","22-06-1995",10,"description","style",1);
    @BeforeEach
    public void setUp() {
        eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
        eventList.add(event3);
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
    public void testSave() {
        Event eventNew = new Event(4L,"Evento 4","22-06-1995",10,"description","style",1);

        when(eventRepository.save(any(Event.class))).thenReturn(eventNew);

        Event result = eventService.save(eventNew);

        assertThat(result.getId()).isEqualTo(4L);
        assertThat(result.getTitle()).isEqualTo("Evento 4");
        assertThat(result.getMax_participants()).isEqualTo(10);
        assertThat(result.getDescription()).isEqualTo("description");
        assertThat(result.getActual_participants()).isEqualTo(1);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(eventRepository).deleteById(id);
        eventService.deleteById(id);
        Optional<Event> eventDeleted = eventService.findById(id);
        verify(eventRepository).deleteById(id);
        assertThat(eventDeleted).isEmpty();
    }

    @Test
    public void findById_shouldReturnEvent_WhenEventExists(){
        Long id = 1L;
        when(eventRepository.findById(id)).thenReturn(Optional.of(event1));
        Optional<Event> result = eventService.findById(id);
        assertThat(result).isNotEmpty();
        assertThat(result.get().getId()).isEqualTo(1L);
        assertThat(result.get().getTitle()).isEqualTo("Evento 1");
        assertThat(result.get().getMax_participants()).isEqualTo(10);
    }

    @Test
    public void updateEvent_shouldUpdateEvent(){
        Long id = 1L;
        Event eventUpdated = new Event(1L,"Evento 1","22-06-1995",10,"description","style",1);
        when(eventRepository.findById(id)).thenReturn(Optional.of(event1));
        when(eventRepository.save(event1)).thenReturn(eventUpdated);
        Event result = eventService.updateEvent(id, eventUpdated);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Evento 1");
        assertThat(result.getMax_participants()).isEqualTo(10);
        assertThat(result.getDescription()).isEqualTo("description");
        assertThat(result.getActual_participants()).isEqualTo(1);
    }

}
