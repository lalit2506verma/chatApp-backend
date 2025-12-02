package com.chat_application.exceptions;


public class RoomNotFoundException extends RuntimeException {
    private String message;

    public RoomNotFoundException() {
        super("Room Not found");
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
