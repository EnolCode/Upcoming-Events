package com.Upcoming.Events.demo.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.repositories.EventRepository;

@Service
public class EventServiceImpl implements BaseService<Event> {

  @Autowired
  private EventRepository eventRepository;

  public EventServiceImpl(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  @Transactional
  public List<Event> findAll() {
    return eventRepository.findAll();
  }

  @Override
  @Transactional
  public Page<Event> findAll(Pageable pageable) {
    return eventRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public Event save(Event event) {
    if (event.getStyle().equals("dance")) {
      event.setStyle("dance.png");
    }
    if (event.getStyle().equals("pop")) {
      event.setStyle("pop.png");
    }
    if (event.getStyle().equals("rock")) {
      event.setStyle("rock.png");
    }
    if (event.getStyle().equals("latina")) {
      event.setStyle("latina.png");
    }
    if (event.getStyle().equals("alternativa")) {
      event.setStyle("alternativa.png");
    }
    if (event.getStyle().equals("hiphop")) {
      event.setStyle("hiphop.png");
    }
    if (event.getStyle().equals("metal")) {
      event.setStyle("metal.png");
    }
    return eventRepository.save(event);
  }

  @Override
  @Transactional
  public Optional<Event> findById(Long id) {
    return eventRepository.findById(id);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    eventRepository.deleteById(id);

  }

  @Transactional
  public Event updateEvent(Long id, Event eventDetails) {
    Event event = findById(id).orElseThrow(null);
    event.setTitle(eventDetails.getTitle());
    event.setActual_participants(eventDetails.getActual_participants());
    event.setDate_hour(eventDetails.getDate_hour());
    event.setDescription(eventDetails.getDescription());
    event.setMax_participants(eventDetails.getMax_participants());
    event.setStyle(eventDetails.getStyle());
      return save(event);
  }
}
