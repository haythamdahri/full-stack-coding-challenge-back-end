package com.challenge.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * Role entity
 * roles table in database
 * properties modifier is private
 * default and all args constructors are generated automatically (byte code is auto generated)
 * properties getters and setters are generated automatically (byte code is auto generated)
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Role {

    /**
     * User identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * User username property
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    private RoleEnum roleName;

    /**
     * collection of users
     * Annotation @ManyToMany to express relationship between Role and User
     * a User can have multiple roles
     * a Role can be affected to many users
     * Add property mapping
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Collection<User> users;


}
