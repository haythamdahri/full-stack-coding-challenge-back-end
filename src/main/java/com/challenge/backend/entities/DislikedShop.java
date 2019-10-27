package com.challenge.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author HAYTHAM DAHRI
 * DislikedShop entity
 * disliked_shops table in database
 * @constraint user_id and shop_id must be unique
 * properties modifier is private
 * default constructor is generated automatically (byte code is auto generated)
 * properties getters and setters are generated automatically (byte code is auto generated)
 */
@Entity
@Table(name = "disliked_shops", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "shop_id"})})
@Data
@NoArgsConstructor
public class DislikedShop {

    /**
     * DislikedShop identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * user property
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * shop property
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "shop_id")
    private Shop shop;

    /**
     * date property
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    /**
     * version property to handle column version after updates to prevent the loss of updates anomaly
     */
    @Version
    private int version;

    /**
     * Constructor with custom properties method
     */
    public DislikedShop(Long id, User user, Shop shop) {
        this.id = id;
        this.user = user;
        this.shop = shop;
        this.date = new Date();
    }

}
