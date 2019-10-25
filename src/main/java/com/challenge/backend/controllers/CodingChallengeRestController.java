package com.challenge.backend.controllers;

import com.challenge.backend.entities.Shop;
import com.challenge.backend.entities.User;
import com.challenge.backend.services.DislikedShopService;
import com.challenge.backend.services.ShopService;
import com.challenge.backend.services.UserService;
import com.challenge.backend.utils.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;

/**
 * Main application rest controller
 * Perform some special tasks when built-in rest does not
 * CrossOrigin all to allow front-end application to access to repository from an other domain
 */
@RestController
@RequestMapping(value = "/rest/v1")
@CrossOrigin("*")
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
     * UserUtility property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private UserUtility userUtility;

    /**
     * Home page rest end point
     */
    @RequestMapping(value = "/")
    public String helloPage() {
        return "Hello world, server is working properly";
    }

    /**
     * Save a user rest end point
     */
    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        // Save user in database
        return this.userService.saveUser(user);
    }

    /**
     * Get user shops
     */
    @RequestMapping(value = "/near-by-shops", method = RequestMethod.GET)
    public Collection<Shop> getNearByShops(Principal principal) {
        // Retrieve connected user from database
        User user = this.userUtility.getAuthenticatedUser();
        // Retrieve allowed shops to be sent to the connected user
        Collection<Shop> shops = this.shopService.getShops();
        // Exclude preferred and disliked shops less than two hours
        this.dislikedShopService.getDislikedShops(user.getId(), new Date()).forEach(dislikedShop -> {
            shops.remove(dislikedShop.getShop());
        });
        shops.removeAll(user.getPreferredShops());
        // return the results
        return shops;
    }

    /**
     * Get a shop image
     */
    @GetMapping("/shop/image/{id}")
    public ResponseEntity<byte[]> showProductImage(@PathVariable(value = "id", required = true) Long id, HttpServletResponse response) throws IOException {
        Shop shop = this.shopService.getShop(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(shop.getImage(), headers, HttpStatus.OK);
    }

}
