package com.challenge.backend.dao;

import com.challenge.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author HAYTHAM DAHRI
 * User entity repository class
 * Repository accessor to user data in database
 */
@Repository
@RepositoryRestResource
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method to get a user from database using the criteria of his email
     * @return Optional of User
     */
    public Optional<User> findByEmail(String email);

}
