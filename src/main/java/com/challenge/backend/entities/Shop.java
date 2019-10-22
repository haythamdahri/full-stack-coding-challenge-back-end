package com.challenge.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * Shop entity
 * shops table in database
 * properties modifier is private
 * default and all args constructors are generated automatically (byte code is auto generated)
 * properties getters and setters are generated automatically (byte code is auto generated)
 */
@Entity
@Table(name = "shops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    /**
     * Shop identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * shop name property
     */
    @Column(name = "name")
    private String name;

    /**
     * shop image binary property
     * Using binary storage to hold security issues
     */
    @Lob
    @Column(name="image")
    private byte[] image;

    /**
     * distance property
     */
    @Column(name = "distance")
    private int distance;

    /**
     * collection of shops users
     * Annotation @ManyToMany to express relationship between Shop and User
     * a Shop can be preferred by many users
     * a User can have multiple preferred shops
     * eager fetch to get preferred shops when getting the user from database
     * Set the property mapping
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "preferredShops")
    private Collection<User> users;

}
