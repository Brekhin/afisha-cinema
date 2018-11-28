package com.brekhin.gateway.exception;

import com.brekhin.gateway.exception.service.Service;
import org.springframework.http.HttpStatus;

public class GatewayAPIException extends RuntimeException {

    private static final long serialVersionUID = -477189103300903771L;

    private Service service;
    private HttpStatus httpStatus;

    public GatewayAPIException(Service service, HttpStatus httpStatus ) {
        super();
        this.service = service;
        this.httpStatus = httpStatus;
    }

    public GatewayAPIException(Service service, HttpStatus httpStatus, String message) {
        super(message);
        this.service = service;
        this.httpStatus = httpStatus;
    }

    public GatewayAPIException(Service service, HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.service = service;
        this.httpStatus = httpStatus;
    }

    public GatewayAPIException(Service service, HttpStatus httpStatus, Throwable cause) {
        super(cause);
        this.service = service;
        this.httpStatus = httpStatus;
    }

    public Service getService() {
        return service;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
