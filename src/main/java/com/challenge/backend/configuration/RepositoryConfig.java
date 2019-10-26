package com.challenge.backend.configuration;

import com.challenge.backend.entities.DislikedShop;
import com.challenge.backend.entities.Role;
import com.challenge.backend.entities.Shop;
import com.challenge.backend.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Rest Repository configuration class that extends RepositoryRestConfigurerAdapter
 * Main functionality is to configure rest api to expose entities id
 */
@Configuration
public class RepositoryConfig  extends RepositoryRestConfigurerAdapter {

    /**
     * Redefine method
     * Force id as a returned field in rest api
     * Allow origins
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class, Role.class, Shop.class, DislikedShop.class);
        config.getCorsRegistry().addMapping("/**")
                .allowedOrigins("http://localhost:4200");
    }

}
