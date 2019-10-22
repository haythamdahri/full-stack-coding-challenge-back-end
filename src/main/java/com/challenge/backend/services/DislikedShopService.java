package com.challenge.backend.services;

import com.challenge.backend.entities.DislikedShop;

import java.util.Collection;

/**
 * @author HAYTHAM DAHRI
 * DislikedShopService interface
 * Implementing business logic
 */
public interface DislikedShopService {

    DislikedShop saveDislikedShop(DislikedShop dislikedShop);

    DislikedShop getDislikedShop(Long id);

    Boolean deleteDislikedShop(Long id);

    public Collection<DislikedShop> getDislikedShops();

}
