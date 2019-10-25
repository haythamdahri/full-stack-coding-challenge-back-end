package com.challenge.backend.dao;

import com.challenge.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author HAYTHAM DAHRI
 * User entity repository class
 * Repository accessor to user data in database
 * CrossOrigin all to allow front-end application to access to repository from an other domain
 */
@Repository
@RepositoryRestResource
@Transactional
@CrossOrigin(value = "*")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method to get a user from database using the criteria of his email
     * @return Optional of User
     */
    public Optional<User> findByEmail(String email);

    /**
     * Method to get a Boolean is a user has the passed email from database
     * @return Boolean
     */
    public boolean existsByEmail(@Param(value = "email") String email);

}
