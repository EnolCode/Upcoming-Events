package com.upcoming.events.demo.security;

import org.springframework.security.core.GrantedAuthority;
<<<<<<< HEAD

import com.Upcoming.Events.demo.models.Authority;
=======
import com.upcoming.events.demo.models.Authority;
>>>>>>> origin/develop


public class SecurityAuthority implements GrantedAuthority{
    
    private final Authority authority;

 
    public SecurityAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getName().toString();
    }
}

