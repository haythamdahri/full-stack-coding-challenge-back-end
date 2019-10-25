package com.challenge.backend;

import com.challenge.backend.entities.Role;
import com.challenge.backend.entities.RoleEnum;
import com.challenge.backend.entities.Shop;
import com.challenge.backend.entities.User;
import com.challenge.backend.services.DislikedShopService;
import com.challenge.backend.services.RoleService;
import com.challenge.backend.services.ShopService;
import com.challenge.backend.services.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;
import java.io.FileInputStream;

/**
 * Entry point for this project.
 *
 * @author HAYTHAM DAHRI
 */
@SpringBootApplication
@Configuration
@Transactional
@CrossOrigin(origins = "*")
public class BackendCodingChallengeApplication implements CommandLineRunner {

    /**
     * Inject an instance of ShopService
     */
    @Autowired
    private ShopService shopService;

    /**
     * Inject an instance of UserService
     */
    @Autowired
    private UserService userService;

    /**
     * Inject an instance of RoleService
     */
    @Autowired
    private RoleService roleService;

    /**
     * Inject an instance of DislikedShopService
     */
    @Autowired
    private DislikedShopService dislikedShopService;

    /**
     * Inject an instance of BCryptPasswordEncoder
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BackendCodingChallengeApplication.class, args);
    }

    /**
     * The executed method after application start
     */
    @Override
    public void run(String... args) throws Exception {
        // Email to be used for persisting and fetching
        String email = "haytham.dahri@gmail.com";

        // Adding roles if not existed
        if (this.roleService.getRoles().size() == 0) {
            this.roleService.saveRole(new Role(null, RoleEnum.ROLE_USER, null));
            this.roleService.saveRole(new Role(null, RoleEnum.ROLE_ADMIN, null));
        }
        // Adding shops if not existed
        if( this.shopService.getShops().size() == 0 ) {
            File file = new File("/home/haytham/Downloads/final_5da0ab64c3971b0014b563f2_762724.jpeg");
            Shop shop = new Shop(null, "MyShop", IOUtils.toByteArray(new FileInputStream(file)), 201, null);
            shop = this.shopService.saveShop(shop);
            System.out.println(shop);
        }
        // Adding users if not existed
        if (this.userService.getUsers().size() == 0) {
            Role roleUser = this.roleService.getRole(RoleEnum.ROLE_USER);
            Role roleAdmin = this.roleService.getRole(RoleEnum.ROLE_ADMIN);
            System.out.println(roleUser);
            System.out.println(roleAdmin);
            User user = new User(null, "haytham_dahri", email, this.bCryptPasswordEncoder.encode("toortoor"), true, null, null);
            user.addRole(roleUser);
            user.addRole(roleAdmin);
            user = this.userService.saveUser(user);
            System.out.println("Saved user ==> " + user.getEmail());
        } else {
            // fetch user and display him
            User user = this.userService.getUser(email);
            System.out.println("Fetched user ==> " + user.getEmail());
        }

        /**
         // Add shops - Build images bytes
         String shopsImagesPath = System.getProperty("user.dir") + "/src/main/resources/uploads/shops";
         // Images
         byte[] alpha55 = IOUtils.toByteArray(new FileInputStream(new File(shopsImagesPath + "/alpha55.jpg")));
         byte[] moroccoMall = IOUtils.toByteArray(new FileInputStream(new File(shopsImagesPath + "/morocco-mall.png")));
         byte[] megaMall = IOUtils.toByteArray(new FileInputStream(new File(shopsImagesPath + "/mega-mall.jpg")));
         byte[] zara = IOUtils.toByteArray(new FileInputStream(new File(shopsImagesPath + "/zara.jpg")));
         // Shop objects
         Arrays.asList(new Shop(null, "Alpha 55", alpha55, 20, null), new Shop(null, "Morocco Mall", moroccoMall, 60, null),
         new Shop(null, "Mega Mall", megaMall, 80, null), new Shop(null, "ZARA", zara, 2, null))
         .forEach(shop -> {
         this.shopService.saveShop(shop);
         });
         System.out.println("Shops has been saved successfully");
         */
    }
}
