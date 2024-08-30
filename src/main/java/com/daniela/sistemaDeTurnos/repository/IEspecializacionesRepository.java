package com.daniela.sistemaDeTurnos.repository;

import com.daniela.sistemaDeTurnos.model.Especializaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEspecializacionesRepository extends JpaRepository<Especializaciones, Long> {

    List<Especializaciones> findByNombre(String nombre);

}
