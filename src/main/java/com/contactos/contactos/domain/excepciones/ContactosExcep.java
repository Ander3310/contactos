package com.contactos.contactos.domain.excepciones;

public class ContactosExcep extends RuntimeException {
    public ContactosExcep(String message) {
        super(message);
    }

    public ContactosExcep(String message, Throwable cause) {
        super(message, cause);
    }
}
