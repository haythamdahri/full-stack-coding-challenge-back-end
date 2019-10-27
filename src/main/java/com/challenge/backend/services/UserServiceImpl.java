package com.challenge.backend.services;

import com.challenge.backend.dao.UserRepository;
import com.challenge.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * UserServiceImpl class
 * UserService interface implementor
 */
@Service
public class UserServiceImpl implements UserService {

    /***
     * Inject a UserRepository instance
     */
    @Autowired
    private UserRepository userRepository;

    /***
     * Inject a BCryptPasswordEncoder instance
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Save the user passed as argument in the database using the dao repository
     *
     * @return User
     */
    @Override
    public User saveUser(User user) {
        // Enable user account
        user.setEnabled(true);
        // Encode user password before persisting
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    /**
     * Get a user from database based on his id using the dao repository
     *
     * @return User or null
     */
    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * Get a user from database based on his email using the dao repository
     *
     * @return User or null
     */
    @Override
    public User getUser(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Delete a user from database based on his id using the dao repository
     *
     * @return true in all cases
     */
    @Override
    public Boolean deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return true;
    }

    /**
     * Get all users from database using the dao repository
     *
     * @return Collection of users
     */
    @Override
    public Collection<User> getUsers() {
        return this.userRepository.findAll();
    }
}
