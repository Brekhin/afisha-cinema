package com.brekhin.ticket.exception;

public class NotFoundTicketException extends RuntimeException {
    private static final long serialVersionUID = -6159697026914506296L;

    public NotFoundTicketException() {
        super();
    }

    public NotFoundTicketException(String message) {
        super(message);
    }
}
