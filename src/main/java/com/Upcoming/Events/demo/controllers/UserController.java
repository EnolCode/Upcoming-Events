package com.upcoming.events.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

import com.Upcoming.Events.demo.models.User;
import com.Upcoming.Events.demo.services.UserServiceImpl;

@RestController
@RequestMapping("/api/events")
=======
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.upcoming.events.demo.models.User;
import com.upcoming.events.demo.services.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
>>>>>>> origin/develop
public class UserController {

 @Autowired
 private UserServiceImpl userService;

 @PostMapping(value ="", consumes = "application/*")
   public ResponseEntity<?> create(@RequestBody User user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
   }

   @GetMapping
   public List<User> getAll() {
    return userService.findAll();
   }

   @PutMapping("/{id}")
   public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable Long id) {
    Optional<User> user = userService.findById(id);
    if (!user.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    user.get().setUsername(userDetails.getUsername());
    user.get().setPassword(userDetails.getPassword());
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
    if (!userService.findById(id).isPresent()) {
        return ResponseEntity.notFound().build();
   }
   userService.deleteById(id);
   return ResponseEntity.ok().build();
}
}

