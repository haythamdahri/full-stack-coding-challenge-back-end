package com.challenge.backend.controllers;

import com.challenge.backend.entities.DislikedShop;
import com.challenge.backend.entities.Shop;
import com.challenge.backend.entities.User;
import com.challenge.backend.exceptions.ShopException;
import com.challenge.backend.services.DislikedShopService;
import com.challenge.backend.services.ShopService;
import com.challenge.backend.services.UserService;
import com.challenge.backend.utils.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HAYTHAM DAHRI
 * Main application rest controller
 * Perform some special tasks when built-in rest does not
 * CrossOrigin all to allow front-end application to access to repository from an other domain
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
     * UserUtility property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private UserUtility userUtility;

    /**
     * BCryptPasswordEncoder property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Home page rest end point
     */
    @RequestMapping(value = "/")
    public String helloPage() {
        return "Hello world, server is working properly";
    }

    /**
     * Save a user rest end point
     * @return User
     */
    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        // Enable user account
        user.setEnabled(true);
        // Encode user password before persisting
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        // Save user in database
        return this.userService.saveUser(user);
    }

    /**
     * Get user near shops
     * @return Collection of shops
     */
    @RequestMapping(value = "/near-by-shops", method = RequestMethod.GET)
    public Collection<Shop> getNearByShops(@RequestParam(name = "search", required = false) String search, Principal principal) {
        // Retrieve connected user id from database
        Long userId = this.userUtility.getAuthenticatedUser().getId();
        // Create shop collection
        Collection<Shop> shops = new ArrayList<>();
        // Check searching criteria
        if( search == null ) {
            shops = this.shopService.getUserNearShops(userId);
        } else {
            shops = this.shopService.getCustomUserNearShops(userId, search);
        }
        // return shops
        return shops;
    }

    /**
     * Get user preferred shops
     * @return Collection of shops
     */
    @RequestMapping(value = "/user-preferred-shops", method = RequestMethod.GET)
    public Collection<Shop> getUserPreferredShops() {
        // Retrieve connected user from database
        User user = this.userUtility.getAuthenticatedUser();
        // Create preferred shops collection
        Collection<Shop> shops = user.getPreferredShops();
        // Return user preferred shops
        return shops;
    }

    /**
     * Get a shop image
     * @return ResponseEntity
     */
    @RequestMapping(value = "/shop/image/{id}")
    public ResponseEntity<byte[]> showProductImage(@PathVariable(value = "id", required = true) Long id, HttpServletResponse response) throws IOException {
        Shop shop = this.shopService.getShop(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(shop.getImage(), headers, HttpStatus.OK);
    }

    /**
     * Add a shop to the current user preferred shops
     * @return ResponseEntity
     */
    @RequestMapping(value = "/add-user-shop/{id}", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Object> addShopToUserPreferredShop(@PathVariable(value = "id", required = true) Long shopId) {
        // Create response object content
        Map<Object, Object> data = new HashMap<>();
        // Perform business logic
        try {
            // Retrieve connected user
            User user = this.userUtility.getAuthenticatedUser();
            // Retrieve the shop
            Shop shop = this.shopService.getShop(shopId);
            // Check if shop exists to throw a new exception
            if( shop == null ) {
                throw new ShopException("No shops has been found with id " + shopId);
            }
            // Add the shop to the user preferred shops
            user.addPreferredShop(shop);
            // Save the user using the user service
            this.userService.saveUser(user);
            // Put success message and status
            data.put("status", true);
            data.put("message", "Shop has been added to your preferences successfully");
        }
        catch(ShopException ex) {
            System.out.println(ex.getMessage());
            data.put("status", false);
            data.put("message", ex.getMessage());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            data.put("status", false);
            data.put("message", "An error occurred, please try again!");
        }
        // Return response
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }

    /**
     * Add a shop to the current user disliked shops
     * @return ResponseEntity
     */
    @RequestMapping(value = "/dislike-shop/{id}", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Object> addShopDislikedShop(@PathVariable(value = "id", required = true) Long shopId) {
        // Create response object content
        Map<Object, Object> data = new HashMap<>();
        // Perform business logic
        try {
            // Retrieve connected user
            User user = this.userUtility.getAuthenticatedUser();
            // Retrieve the shop
            Shop shop = this.shopService.getShop(shopId);
            // Check if shop exists to throw a new exception
            if( shop == null ) {
                throw new ShopException("No shops has been found with id " + shopId);
            }
            // Create a disliked shop object and save it
            DislikedShop dislikedShop = this.dislikedShopService.saveOrUpdate(user.getId(), shop.getId());
            // Put success message and status
            data.put("status", true);
            data.put("message", "Shop has been disliked successfully");
        }
        catch(ShopException ex) {
            System.out.println(ex.getMessage());
            data.put("status", false);
            data.put("message", ex.getMessage());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            data.put("status", false);
            data.put("message", "An error occurred, please try again!");
        }
        // Return response
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }


    /**
     * Add a shop to the current user preferred shops
     * @return ResponseEntity
     */
    @RequestMapping(value = "/remove-preferred-shop/{id}", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Object> removeShopFromPreferredShops(@PathVariable(value = "id", required = true) Long shopId) {
        // Create response object content
        Map<Object, Object> data = new HashMap<>();
        // Perform business logic
        try {
            // Retrieve connected user
            User user = this.userUtility.getAuthenticatedUser();
            // Retrieve the shop
            Shop shop = this.shopService.getShop(shopId);
            // Check if shop exists to throw a new exception
            if( shop == null ) {
                throw new ShopException("No shops has been found with id " + shopId);
            }
            // Remove shop from user preferred shops list
            user.removePreferredShop(shop);
            // Put success message and status
            data.put("status", true);
            data.put("message", "Shop has been remove from your preferences successfully");
        }
        catch(ShopException ex) {
            System.out.println(ex.getMessage());
            data.put("status", false);
            data.put("message", ex.getMessage());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            data.put("status", false);
            data.put("message", "An error occurred, please try again!");
        }
        // Return response
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }
}
