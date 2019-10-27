package com.challenge.backend.exceptions;

import lombok.Data;

/**
 * @author HAYTHAM DAHRI
 * Shop exception class
 */
@Data
public class ShopException extends Exception {

    /**
     * Create necessary constructors
     */
    public ShopException() {
        super();
    }

    public ShopException(String message) {
        super(message);
    }

    public ShopException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
