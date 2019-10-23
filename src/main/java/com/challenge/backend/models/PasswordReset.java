package com.challenge.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PasswordReset model class to send wrap sent data to the user
 * properties modifier is private
 * default and all args constructors are generated automatically (byte code is auto generated)
 * properties getters and setters are generated automatically (byte code is auto generated)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordReset {

    /**
     * token property
     */
    public String token;

    /**
     * newPassword property
     */
    public String newPassword;

}
