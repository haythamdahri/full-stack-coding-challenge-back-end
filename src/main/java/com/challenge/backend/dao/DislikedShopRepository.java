package com.challenge.backend.dao;

import com.challenge.backend.entities.DislikedShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * DislikedShop entity repository class
 * Repository accessor to disliked_shops data in database
 */
@Repository
@RepositoryRestResource
public interface DislikedShopRepository extends JpaRepository<DislikedShop, Long> {

    /**
     * Find disliked shops by a user in the last two hours
     */
    @Query(value = "FROM DislikedShop as ds where ds.user.id = :userId and ((day(current_timestamp) *24 + hour(current_timestamp)) -(day(ds.date)*24 + hour(ds.date))) <= 2")
    public Collection<DislikedShop> findUserNewDislikedShops(@Param(value = "userId") Long userId);

    /**
     * Find a shop by a user and shop
     */
    public DislikedShop findByUserIdAndShopId(Long userId, Long shopId);

}
