package com.daniela.sistemaDeTurnos.repository;

import com.daniela.sistemaDeTurnos.model.Sucursales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISucursalesRepository extends JpaRepository<Sucursales, Long> {

    List<Sucursales> findByBarrio(String barrio);

    List<Sucursales> findByDireccion(String direccion);

}
