package com.challenge.backend.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author HAYTHAM DAHRI
 * Exception response representation object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    /**
     * Exception response time property
     */
    private Date timestamp;

    /**
     * Exception response message property
     */
    private String message;

    /**
     * Exception response details property
     */
    private String details;

}
