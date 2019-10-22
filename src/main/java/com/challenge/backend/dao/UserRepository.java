package com.challenge.backend.dao;

import com.challenge.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author HAYTHAM DAHRI
 * User entity repository class
 * Repository accessor to user data in database
 */
@Repository
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
}
