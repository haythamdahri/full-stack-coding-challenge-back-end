package com.challenge.backend.services;

import com.challenge.backend.dao.ShopRepository;
import com.challenge.backend.entities.Shop;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * ShopServiceImpl class
 * ShopService interface implementor
 */
public class ShopServiceImpl implements ShopService {

    /***
     * Inject a ShopRepository instance
     */
    @Autowired
    private ShopRepository shopRepository;

    /**
     * Save the shop passed as argument in the database using the dao repository
     *
     * @return Shop
     */
    @Override
    public Shop saveShop(Shop shop) {
        return this.shopRepository.save(shop);
    }

    /**
     * Get a shop from database based on his id using the dao repository
     *
     * @return User or null
     */
    @Override
    public Shop getShop(Long id) {
        return this.shopRepository.findById(id).orElse(null);
    }

    /**
     * Delete a shop from database based on his id using the dao repository
     *
     * @return true in all cases
     */
    @Override
    public Boolean deleteShop(Long id) {
        this.shopRepository.deleteById(id);
        return true;
    }

    /**
     * Get all shops from database using the dao repository
     *
     * @return Collection of shops
     */
    @Override
    public Collection<Shop> getShops() {
        return this.shopRepository.findAll();
    }
}
