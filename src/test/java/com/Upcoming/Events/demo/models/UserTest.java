package com.Upcoming.Events.demo.models;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Upcoming.Events.demo.models.User;

import javax.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testCountryPersistedInDatabase() {
        User paco = new User("Paco", "pass", null);

        entityManager.persist(paco);
        entityManager.flush();

        User retrievedUser = entityManager.find(User.class, paco.getId());

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getUsername()).isEqualTo("Paco");
    }

    @Test
    void testName() {
        
    }

    
}