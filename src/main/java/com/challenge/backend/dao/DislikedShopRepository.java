package com.challenge.backend.dao;

import com.challenge.backend.entities.DislikedShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author HAYTHAM DAHRI
 * DislikedShop entity repository class
 * Repository accessor to disliked_shop data in database
 */
@Repository
@RepositoryRestResource
@Transactional
public interface DislikedShopRepository extends JpaRepository<DislikedShop, Long> {
}
