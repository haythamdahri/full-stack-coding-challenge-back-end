package com.challenge.backend.dao;

import com.challenge.backend.entities.DislikedShop;
import com.challenge.backend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

/**
 * @author HAYTHAM DAHRI
 * DislikedShop entity repository class
 * Repository accessor to disliked_shop data in database
 */
@Repository
@RepositoryRestResource
@Transactional
@CrossOrigin(value = "*")
public interface DislikedShopRepository extends JpaRepository<DislikedShop, Long> {

    public Collection<DislikedShop> findByUserIdAndDateBefore(Long userId, Date date);

}
