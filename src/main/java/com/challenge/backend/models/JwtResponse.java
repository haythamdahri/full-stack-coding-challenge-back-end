package com.challenge.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * JwtResponse model class to send wrap sent data to the user
 * properties modifier is private
 * default and all args constructors are generated automatically (byte code is auto generated)
 * properties getters and setters are generated automatically (byte code is auto generated)
 */
@Data
public class JwtResponse implements Serializable {

    /**
     * jwtToken property
     */
    private final String jwtToken;

    /**
     * Constructor with arguments
     */
    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
