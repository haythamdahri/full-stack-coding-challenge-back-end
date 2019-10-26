package com.challenge.backend.dao;

import com.challenge.backend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * Shop entity repository class
 * Repository accessor to shop data in database
 */
@Repository
@RepositoryRestResource
public interface ShopRepository extends JpaRepository<Shop, Long> {

    /**
     * Fetch shops which are not disliked in the last two hours and not in preferred list of the passed user id
     */
    @Query(value = "FROM Shop as s where s.id not in (:newDislikedShops) and s.id not in (:preferredShops) ORDER BY s.distance DESC")
    Collection<Shop> findShopsNearUser(@Param(value = "preferredShops") Collection<Long> preferredShopsIds, @Param(value = "newDislikedShops") Collection<Long> newDislikedShops);

    /**
     * Fetch shops which are not disliked in the last two hours and not in preferred list and like the passed search name of the user id
     */
    @Query(value = "FROM Shop as s where s.id not in (:newDislikedShops) and s.id not in (:preferredShops) and s.name like %:search% ORDER BY s.distance DESC")
    Collection<Shop> findCustomShopsNearUser(@Param(value = "preferredShops") Collection<Long> preferredShopsIds, @Param(value = "newDislikedShops") Collection<Long> newDislikedShops, @Param(value = "search") String search);

}
