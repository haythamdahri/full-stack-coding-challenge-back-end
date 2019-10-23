package com.challenge.backend.services;

import com.challenge.backend.entities.Role;
import com.challenge.backend.entities.RoleEnum;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * RoleService interface
 * Implementing business logic
 */
public interface RoleService {

    Role saveRole(Role role);

    Role getRole(Long id);

    Role getRole(RoleEnum roleEnum);

    Boolean deleteRole(Long id);

    Collection<Role> getRoles();

}
