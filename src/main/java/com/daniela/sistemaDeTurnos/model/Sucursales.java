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
@Table(name = "sucursales")
public class Sucursales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String barrio;

    private String direccion;

    private List<String> especializaciones = new ArrayList<>();

    @JoinTable(
            name = "doctor_sucursal",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctores> doctores = new ArrayList<>();


    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<String> getEspecializaciones() {
        return especializaciones;
    }

    public void setEspecializaciones(List<String> especializaciones) {
        this.especializaciones = especializaciones;
    }

    public List<Doctores> getDoctores() {
        return doctores;
    }

    public void setDoctores(List<Doctores> doctores) {
        this.doctores = doctores;
    }
}

