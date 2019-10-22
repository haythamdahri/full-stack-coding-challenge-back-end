package com.challenge.backend.services;

import com.challenge.backend.entities.User;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * UserService interface
 * Implementing business logic
 */
public interface UserService {

    User saveUser(User user);

    User getUser(Long id);

    Boolean deleteUser(Long id);

    public Collection<User> getUsers();

}
