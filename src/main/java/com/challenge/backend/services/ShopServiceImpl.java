package com.challenge.backend.services;

import com.challenge.backend.dao.ShopRepository;
import com.challenge.backend.entities.DislikedShop;
import com.challenge.backend.entities.Shop;
import com.challenge.backend.entities.User;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author HAYTHAM DAHRI
 * ShopServiceImpl class
 * ShopService interface implementor
 */
@Service
public class ShopServiceImpl implements ShopService {

    /**
     * Inject a ShopRepository instance
     */
    @Autowired
    private ShopRepository shopRepository;

    /**
     * Inject a UserService instance
     */
    @Autowired
    private UserService userService;

    /**
     * Inject a DislikedShopService instance
     */
    @Autowired
    private DislikedShopService dislikedShopService;

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

    /**
     * Get user near shops from database using the dao repository
     * Final shops list is ordered by distance
     * @return Collection of shops
     */
    @Override
    public Collection<Shop> getUserNearShops(Long userId) {
        // Retrieve user and his preferred shops
        User user = this.userService.getUser(userId);
        // Retrieve user preferred shops id list
        Collection<Long> preferredShopsIds = user.getPreferredShops().stream().map(Shop::getId).collect(Collectors.toList());
        // Retrieve user new disliked shops ids that were added in the last two hours
        Collection<DislikedShop> dislikedShops = this.dislikedShopService.getDislikedShops(userId, DateUtils.addHours(new Date(), 2));
        Collection<Long> dislikedShopsIds = dislikedShops.stream()
                .map(DislikedShop::getShop).collect(Collectors.toList()).stream().map(Shop::getId).collect(Collectors.toList());
        // Check if any list is empty to avoid sql exception while executing HQL queries in the repository class
        if( preferredShopsIds.isEmpty()) {
            // Add a value which does not exists surely
            preferredShopsIds.add(-1L);
        }
        if( dislikedShopsIds.isEmpty()) {
            // Add a value which does not exists surely
            dislikedShopsIds.add(-1L);
        }
        // Fetch data using DAO repository
        return this.shopRepository.findShopsNearUser(preferredShopsIds, dislikedShopsIds);
    }


    /**
     * Get custom user near shops from database using the dao repository
     * Final shops list is ordered by distance
     * @return Collection of shops
     */
    @Override
    public Collection<Shop> getCustomUserNearShops(Long userId, String search) {
        // Retrieve user and his preferred shops
        User user = this.userService.getUser(userId);
        // Retrieve user preferred shops id list
        Collection<Long> preferredShopsIds = user.getPreferredShops().stream().map(Shop::getId).collect(Collectors.toList());
        // Retrieve user new disliked shops ids that were added in the last two hours
        Collection<DislikedShop> dislikedShops = this.dislikedShopService.getDislikedShops(userId, DateUtils.addHours(new Date(), 2));
        Collection<Long> dislikedShopsIds = dislikedShops.stream()
                .map(DislikedShop::getShop).collect(Collectors.toList()).stream().map(Shop::getId).collect(Collectors.toList());
        // Check if any list is empty to avoid sql exception while executing HQL queries in the repository class
        if( preferredShopsIds.isEmpty()) {
            // Add a value which does not exists surely
            preferredShopsIds.add(-1L);
        }
        if( dislikedShopsIds.isEmpty()) {
            // Add a value which does not exists surely
            dislikedShopsIds.add(-1L);
        }
        // Fetch data using DAO repository
        return this.shopRepository.findCustomShopsNearUser(preferredShopsIds, dislikedShopsIds, search);
    }
}
