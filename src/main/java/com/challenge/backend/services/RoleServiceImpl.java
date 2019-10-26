package com.challenge.backend.services;

import com.challenge.backend.dao.RoleRepository;
import com.challenge.backend.entities.Role;
import com.challenge.backend.entities.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * RoleServiceImpl class
 * RoleService interface implementor
 */
@Service
public class RoleServiceImpl implements RoleService {

    /***
     * Inject a RoleRepository instance
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Save the passed shop argument in the database using the dao repository
     *
     * @return Shop
     */
    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    /**
     * Get a role from database based on his id using the dao repository
     *
     * @return Role or null
     */
    @Override
    public Role getRole(Long id) {
        return this.roleRepository.findById(id).orElse(null);
    }


    /**
     * Get a role from database based on the role name using the dao repository
     *
     * @return Role or null
     */
    @Override
    public Role getRole(RoleEnum roleEnum) {
        return this.roleRepository.findByRoleName(roleEnum).orElse(null);
    }

    /**
     * Delete a role from database based on his id using the dao repository
     *
     * @return true in all cases
     */
    @Override
    public Boolean deleteRole(Long id) {
        this.roleRepository.deleteById(id);
        return true;
    }

    /**
     * Get all roles from database using the dao repository
     *
     * @return Collection of roles
     */
    @Override
    public Collection<Role> getRoles() {
        return this.roleRepository.findAll();
    }
}
