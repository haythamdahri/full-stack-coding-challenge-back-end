package com.challenge.backend.utils;

import com.challenge.backend.entities.User;
import com.challenge.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * UserUtility component class
 */
@Component
public class UserUtility {

    /**
     * UserService property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private UserService userService;

    /**
     * Method to retrieve authenticated user
     * @return User
     */
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = authentication.getName();
            return this.userService.getUser(email);
        }
        return null;
    }

}

