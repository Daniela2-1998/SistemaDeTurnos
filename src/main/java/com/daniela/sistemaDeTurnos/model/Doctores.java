package com.daniela.sistemaDeTurnos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "doctores")
public class Doctores extends Usuarios{

    private List<String> especializaciones;

    private Double sueldo;

    private String celular;



    // Getters y setters
    public List<String> getEspecializaciones() {
        return especializaciones;
    }

    public void setEspecializaciones(List<String> especializaciones) {
        this.especializaciones = especializaciones;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


}
