package com.challenge.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HAYTHAM DAHRI
 * JWTRequest model class to handle sent data from the user
 * properties modifier is private
 * default and all args constructors are generated automatically (byte code is auto generated)
 * properties getters and setters are generated automatically (byte code is auto generated)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {

    /**
     * email property
     */
    private String email;

    /**
     * password property
     */
    private String password;

}
