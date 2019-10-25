package com.challenge.backend.services;

import com.challenge.backend.entities.DislikedShop;

import java.util.Collection;
import java.util.Date;

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

    public Collection<DislikedShop> getDislikedShops(Long userId, Date date);

}
