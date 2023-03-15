package com.upcoming.events.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

import com.Upcoming.Events.demo.models.User;
import com.Upcoming.Events.demo.repositories.UserRepository;
=======
import com.upcoming.events.demo.models.User;
import com.upcoming.events.demo.repositories.UserRepository;
>>>>>>> origin/develop

@Service
public class UserServiceImpl implements BaseService<User>{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User save(User User) {
        return userRepository.save(User);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
         userRepository.deleteById(id);
    }
}
