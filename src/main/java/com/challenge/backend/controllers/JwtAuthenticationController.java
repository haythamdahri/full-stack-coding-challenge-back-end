package com.challenge.backend.controllers;

import com.challenge.backend.configuration.JwtTokenUtil;
import com.challenge.backend.models.JwtRequest;
import com.challenge.backend.models.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * JWT authentication controller to handle user identifier
 */
@RestController
@CrossOrigin("*")
public class JwtAuthenticationController {

    /**
     * AuthenticationManager property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * JwtTokenUtil property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * UserDetailsService property
     * Inject an instance from servlet container
     * Performing Dependency Injection
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Authentication end point method
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("Request body: " + authenticationRequest);
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * private method to authenticate user based on email and password that are passed as arguments
     */
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

