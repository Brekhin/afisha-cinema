package com.brekhin.movie.exception;

public class NotFoundMovieException extends RuntimeException {

    private static final long serialVersionUID = -477189107700903771L;


    public NotFoundMovieException() {
        super();
    }

    public NotFoundMovieException(String message) {
        super(message);
    }
}
