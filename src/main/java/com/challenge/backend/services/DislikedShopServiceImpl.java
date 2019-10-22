package com.challenge.backend.services;

import com.challenge.backend.dao.DislikedShopRepository;
import com.challenge.backend.entities.DislikedShop;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * DislikedShopServiceImpl class
 * DislikedShopService interface implementor
 */
public class DislikedShopServiceImpl implements DislikedShopService {

    /***
     * Inject a DislikedShopRepository instance
     */
    @Autowired
    private DislikedShopRepository dislikedShopRepository;

    /**
     * Save the dislikedShop passed as argument in the database using the dao repository
     *
     * @return Shop
     */
    @Override
    public DislikedShop saveDislikedShop(DislikedShop dislikedShop) {
        return this.dislikedShopRepository.save(dislikedShop);
    }

    /**
     * Get a dislikedShop from database based on his id using the dao repository
     *
     * @return User or null
     */
    @Override
    public DislikedShop getDislikedShop(Long id) {
        return this.dislikedShopRepository.findById(id).orElse(null);
    }

    /**
     * Delete a dislikedShop from database based on his id using the dao repository
     *
     * @return true in all cases
     */
    @Override
    public Boolean deleteDislikedShop(Long id) {
        this.dislikedShopRepository.deleteById(id);
        return true;
    }

    /**
     * Get all dislikedShops from database using the dao repository
     *
     * @return Collection of dislikedShops
     */
    @Override
    public Collection<DislikedShop> getDislikedShops() {
        return this.dislikedShopRepository.findAll();
    }
}
