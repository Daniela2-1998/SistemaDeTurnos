package com.daniela.sistemaDeTurnos.repository;

import com.daniela.sistemaDeTurnos.model.Doctores;
import com.daniela.sistemaDeTurnos.model.Pacientes;
import com.daniela.sistemaDeTurnos.model.Sucursales;
import com.daniela.sistemaDeTurnos.model.Turnos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITurnosRepository extends JpaRepository<Turnos, Long> {

    List<Turnos> findBySucursal(Sucursales sucursal);

    List<Turnos> findByDia(LocalDate dia);

    List<Turnos> findByHora(String hora);

    List<Turnos> findByDoctor(Doctores doctor);

    List<Turnos> findByPaciente(Pacientes paciente);


    // Consultas mixtas
    List<Turnos> findBySucursalAndDia(Sucursales sucursal, LocalDate dia);

    List<Turnos> findByDiaAndHora(LocalDate dia, String hora);

    List<Turnos> findByDoctorAndPaciente(Doctores doctor, Pacientes paciente);

    Turnos findByDoctorAndPacienteAndDia(Doctores doctor, Pacientes paciente, LocalDate dia);

}
