package com.brekhin.moviesession.exception;

public class NotFoundSessionException extends RuntimeException{

    private static final long serialVersionUID = -346760434090767410L;

    public NotFoundSessionException(){
        super();
    }

    public NotFoundSessionException(String message){
        super(message);
    }
}
