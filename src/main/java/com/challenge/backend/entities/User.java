package com.challenge.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class User {

    /**
     * User identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

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
     * collection of User preferred shops
     * Annotation @ManyToMany to express relationship between User and Shop
     * a User can have multiple preferred shops
     * a Shop can be preferred by many users
     * eager fetch to get preferred shops when getting the user from database
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_shops", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "shop_id")})
    private Collection<Shop> preferredShops;


}