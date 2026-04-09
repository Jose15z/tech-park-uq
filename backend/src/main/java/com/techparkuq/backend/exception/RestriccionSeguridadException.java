package com.techparkuq.backend.exception;

public class RestriccionSeguridadException extends RuntimeException {

    public RestriccionSeguridadException(String mensaje) {
        super(mensaje);
    }
}