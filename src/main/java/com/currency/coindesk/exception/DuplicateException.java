package com.currency.coindesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.BAD_REQUEST )
public class DuplicateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DuplicateException( String code ) {
        super( "Currency [" + code + "] already exist" );
    }
}
