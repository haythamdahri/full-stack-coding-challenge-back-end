package com.challenge.backend.dao;

import com.challenge.backend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * @author HAYTHAM DAHRI
 * Shop entity repository class
 * Repository accessor to shop data in database
 */
@Repository
@RepositoryRestResource
@Transactional
@CrossOrigin(value = "*")
public interface ShopRepository extends JpaRepository<Shop, Long> {

    /**
     * Fetch shops which are not in passed id list and order the results by distance
     */
    public Collection<Shop> findShopsByIdNotInOrderByDistance(Collection<Long> shopsIds);

}
