package com.daniela.sistemaDeTurnos.dto.response;

import com.daniela.sistemaDeTurnos.model.Doctores;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SucursalesResponseDto {

    private Long id;

    private String barrio;

    private String direccion;

    private List<String> especializaciones = new ArrayList<>();

    private List<Doctores> doctores = new ArrayList<>();

}
