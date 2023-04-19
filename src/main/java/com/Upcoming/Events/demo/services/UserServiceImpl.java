package com.Upcoming.Events.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.Upcoming.Events.demo.Exception.UserAlreadyExistsException;
import com.Upcoming.Events.demo.Exception.UserServiceException;
// import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.models.User;
// import com.Upcoming.Events.demo.repositories.EventRepository;
import com.Upcoming.Events.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements BaseService<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    // private EventRepository eventRepository;

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User save(User User) {
        return userRepository.save(User);
    }

    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User store(User user) {

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User already exist.");
        }
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepository.save(user);

        } catch (DataAccessException e) {
            throw new UserServiceException("Dont save user", e);
        }
    }

    // @Transactional
    // public void registerUserToEvent(Long eventId) {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     String currentUserName = authentication.getName();
    //     User user = userRepository.findByUsername(currentUserName)
    //             .orElseThrow(() -> new RuntimeException("User not found with username " + currentUserName));

    //     Event event = eventRepository.findById(eventId)
    //             .orElseThrow(() -> new RuntimeException("Event not found with id " + eventId));

    //     if (event.getUsers().contains(user)) {
    //         throw new RuntimeException("User is already registered for the event");
    //     }

    //     if (event.getMax_participants() <= event.getActual_participants()) {
    //         throw new RuntimeException("Event is full");
    //     }

    //     event.setActual_participants(event.getActual_participants() + 1);
    //     user.getEvents().add(event);
    //     userRepository.save(user);
    // }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
