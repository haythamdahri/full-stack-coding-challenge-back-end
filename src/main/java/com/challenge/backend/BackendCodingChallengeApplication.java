package com.challenge.backend;

import com.challenge.backend.entities.Shop;
import com.challenge.backend.entities.User;
import com.challenge.backend.services.ShopService;
import com.challenge.backend.services.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

/**
 * Entry point for this project.
 *
 * @author HAYTHAM DAHRI
 */
@SpringBootApplication
@Configuration
public class BackendCodingChallengeApplication implements CommandLineRunner {

    /**
     * Inject an instance of ShopService
     */
    @Autowired
    private ShopService shopService;

    /**
     * Inject an instance of BCryptPasswordEncoder
     */
  /*  @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    public static void main(String[] args) {
        SpringApplication.run(BackendCodingChallengeApplication.class, args);
    }

    /**
     * The executed method after application start
     */
    @Override
    public void run(String... args) throws Exception {
        if( this.shopService.getShops().size() == 0 ) {
            File file = new File("/home/haytham/Downloads/final_5da0ab64c3971b0014b563f2_762724.jpeg");
            Shop shop = new Shop(null, "MyShop", IOUtils.toByteArray(new FileInputStream(file)), 201, null);
            shop = this.shopService.saveShop(shop);
            System.out.println(shop);
        } else {
            Shop shop = this.shopService.getShop(1L);
            String image = Base64.getEncoder().encodeToString(shop.getImage());
            System.out.println(image);
        }
    }
}
