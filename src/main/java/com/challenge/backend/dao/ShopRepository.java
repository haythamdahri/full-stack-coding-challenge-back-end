package com.challenge.backend.dao;

import com.challenge.backend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author HAYTHAM DAHRI
 * Shop entity repository class
 * Repository accessor to shop data in database
 */
@Repository
@RepositoryRestResource
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
