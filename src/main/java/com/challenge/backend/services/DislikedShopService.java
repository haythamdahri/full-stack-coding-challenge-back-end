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

    DislikedShop saveOrUpdate(Long userId, Long shopId);

    DislikedShop getDislikedShop(Long id);

    Boolean deleteDislikedShop(Long id);

    Collection<DislikedShop> getDislikedShops();

    Collection<DislikedShop> getDislikedShops(Long userId);

}
