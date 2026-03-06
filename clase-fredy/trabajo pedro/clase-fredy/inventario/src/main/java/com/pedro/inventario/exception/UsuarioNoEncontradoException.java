package com.pedro.inventario.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
   
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
