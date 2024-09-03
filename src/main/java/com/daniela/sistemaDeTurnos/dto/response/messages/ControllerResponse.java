package com.daniela.sistemaDeTurnos.dto.response.messages;

public class ControllerResponse<T> {

    private boolean exito;

    private String mensaje;

    private T data;


    // Constructor
    public ControllerResponse(boolean exito, String mensaje, T data) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.data = data;
    }


    // Getters y setters
    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
