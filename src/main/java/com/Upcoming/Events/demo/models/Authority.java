package com.Upcoming.Events.demo.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.Upcoming.Events.demo.utils.AuthorityName;

@Entity(name="authorities")
public class Authority {
        // public Authority(AuthorityName author)  
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Authority(AuthorityName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    
}
