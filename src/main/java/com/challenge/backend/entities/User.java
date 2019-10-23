package com.challenge.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * User entity
 * users table in database
 * properties modifier is private
 * default and all args constructors are generated automatically (byte code is auto generated)
 * properties getters and setters are generated automatically (byte code is auto generated)
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class User {

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
    @Column(name = "username")
    private String username;

    /**
     * User email property
     */
    @Column(name = "email", unique = true, insertable = true, updatable = true, nullable = false, length = 255)
    private String email;

    /**
     * User password property
     * password never stored in clear text
     */
    @Column(name = "password")
    private String password;


    /**
     * User enabled status property
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * collection of roles
     * Annotation @ManyToMany to express relationship between User and Role
     * a User can have multiple roles
     * a Role can be affected to many users
     * eager fetch to get preferred shops when getting the user from database
     */
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    /**
     * collection of User preferred shops
     * Annotation @ManyToMany to express relationship between User and Shop
     * a User can have multiple preferred shops
     * a Shop can be preferred by many users
     * eager fetch to get preferred shops when getting the user from database
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_shops", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "shop_id")})
    private Collection<Shop> preferredShops;

    /**
     * Convenient method to add new role to the current user
     */
    public void addRole(Role role) {
        // Check if the roles collection is null
        if( this.roles == null ) {
            this.roles = new ArrayList<>();
        }
        // Add the passed role to the user roles
        this.roles.add(role);
    }

}
