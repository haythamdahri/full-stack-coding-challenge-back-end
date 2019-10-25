package com.challenge.backend.dao;

import com.challenge.backend.entities.Role;
import com.challenge.backend.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author HAYTHAM DAHRI
 * Role entity repository class
 * Repository accessor to role data in database
 */
@Repository
@RepositoryRestResource
@Transactional
@CrossOrigin(value = "*")
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * DAO method to fetch role based on the name
     */
    Optional<Role> findByRoleName(RoleEnum roleEnum);

}
