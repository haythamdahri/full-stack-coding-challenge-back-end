package com.challenge.backend.services;

import com.challenge.backend.entities.Shop;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * ShopService interface
 * Implementing business logic
 */
public interface ShopService {

    Shop saveShop(Shop shop);

    Shop getShop(Long id);

    Boolean deleteShop(Long id);

    public Collection<Shop> getShops();

    public Collection<Shop> getUserNearShops(Long userId);

    public Collection<Shop> getCustomUserNearShops(Long userId, String search);
}
