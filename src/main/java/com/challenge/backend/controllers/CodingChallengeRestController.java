package com.challenge.backend.controllers;

import com.challenge.backend.services.DislikedShopService;
import com.challenge.backend.services.ShopService;
import com.challenge.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main application rest controller
 * Perform some special tasks when built-in rest does not
 */
@RestController
@RequestMapping(value = "/rest/v1")
public class CodingChallengeRestController {

    /**
     * UserService property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private UserService userService;

    /**
     * ShopService property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private ShopService shopService;

    /**
     * DislikedShop Service property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private DislikedShopService dislikedShopService;

    /**
     * Home page rest end point
     */
    @RequestMapping(value = "/")
    public String helloPage() {
        return "Hello world, server is working properly";
    }

}
