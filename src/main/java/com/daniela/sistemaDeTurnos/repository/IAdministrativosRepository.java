package com.daniela.sistemaDeTurnos.repository;

import com.daniela.sistemaDeTurnos.model.Administrativos;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdministrativosRepository extends JpaRepository<Administrativos, Long> {

    List<Administrativos> findByMail(String mail);

    List<Administrativos> findByNombre(String nombre);

    List<Administrativos> findByTipo(String tipo);

    List<Administrativos> findByIdentificacion(String identificacion);

    List<Administrativos> findByEstado(Usuarios.Estado estado);


    // Específicos
    List<Administrativos> findByCargo(String cargo);

    List<Administrativos> findByHorarios(String horarios);

    List<Administrativos> findBySueldo(Double sueldo);

}
