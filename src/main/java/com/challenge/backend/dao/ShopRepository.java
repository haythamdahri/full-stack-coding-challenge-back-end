package com.challenge.backend.dao;

import com.challenge.backend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author HAYTHAM DAHRI
 * Shop entity repository class
 * Repository accessor to shop data in database
 */
@Repository
@RepositoryRestResource
@Transactional
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
