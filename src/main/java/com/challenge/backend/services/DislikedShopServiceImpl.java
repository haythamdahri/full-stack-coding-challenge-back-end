package com.challenge.backend.services;

import com.challenge.backend.dao.DislikedShopRepository;
import com.challenge.backend.entities.DislikedShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * @author HAYTHAM DAHRI
 * DislikedShopServiceImpl class
 * DislikedShopService interface implementor
 */
@Service
public class DislikedShopServiceImpl implements DislikedShopService {

    /***
     * Inject a DislikedShopRepository instance
     */
    @Autowired
    private DislikedShopRepository dislikedShopRepository;

    /***
     * Inject a UserService instance
     */
    @Autowired
    private UserService userService;

    /***
     * Inject a ShopService instance
     */
    @Autowired
    private ShopService shopService;

    /**
     * Save the passed dislikedShop argument in the database using the dao repository
     *
     * @return Shop
     */
    @Override
    public DislikedShop saveDislikedShop(DislikedShop dislikedShop) {
        return this.dislikedShopRepository.save(dislikedShop);
    }

    /**
     * Save or update a dislikedShop object in the database using the dao repository
     *
     * @return Shop
     */
    @Override
    public DislikedShop saveOrUpdate(Long userId, Long shopId){
        try {
            // Check if an object with the passed user and shop already exists or not
            DislikedShop dislikedShop = this.dislikedShopRepository.findByUserIdAndShopId(userId, shopId);
            if (dislikedShop != null) {
                dislikedShop.setDate(new Date());
            } else {
                dislikedShop = new DislikedShop(null, this.userService.getUser(userId), this.shopService.getShop(shopId));
            }
            // Save and return the disliked shop object
            return this.dislikedShopRepository.save(dislikedShop);
        }
        catch(Exception ex) {
            return null;
        }
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

    /**
     * Get passed user argument dislikedShops from database using the dao repository
     *
     * @return Collection of dislikedShops
     */
    @Override
    public Collection<DislikedShop> getDislikedShops(Long userId) {
        return this.dislikedShopRepository.findUserNewDislikedShops(userId);
    }
}
