package com.currency.coindesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class NotExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NotExistException( String message ) {
        super( message );
    }
}
