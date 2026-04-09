package com.techparkuq.backend.exception;

public class AtraccionNoDisponibleException extends RuntimeException {

    public AtraccionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}