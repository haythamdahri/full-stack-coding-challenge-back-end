package com.challenge.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

/**
 * @author HAYTHAM DAHRI
 * Configuration class
 * Define necessary methods depending on business logic
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Inject instance of available DataSource in the Servlet Container
     */
    @Autowired
    private DataSource dataSource;

    /**
     * Inject instance of available BCryptPasswordEncoder in the Servlet Container
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Inject instance of available JwtAuthenticationEntryPoint in the Servlet Container
     */
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * Inject instance of available UserDetailsService in the Servlet Container
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Inject instance of available JwtRequestFilter in the Servlet Container
     */
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * Method to configure how users are authenticated using the redefined service class UserDetailsService
     * Set appropriate password encoder that is used to hash users password
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /* configure AuthenticationManager so that it knows from where to load user for matching credentials
        Use BCryptPasswordEncoder */
        auth.userDetailsService(userDetailsService).passwordEncoder(this.bCryptPasswordEncoder);
    }

    /**
     * Method to provide a bean of type AuthenticationManager in the Servlet Container
     * Bean must be declared here to access method in the super class
     *
     * @return AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Method to configure web resources
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/error", "/css/**", "/js/**", "/images/**", "/less/**", "/metadata/**", "/scss/**", "/sprites/**", "/svgs/**", "/webfonts/**", "/uploads/**");
    }

    /**
     * Method to configure http resources
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // We don't need CSRF in our rest api
        http.csrf().disable();
        // Enable cors
        http.cors().and()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers(
                "/authenticate",
                "/rest/users/search/existsByEmail",
                "/rest/v1/save-user",
                "/rest/v1/shop/image/*"
        ).permitAll().
                antMatchers(HttpMethod.OPTIONS, "/**").permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
