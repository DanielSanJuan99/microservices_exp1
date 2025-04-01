package com.project.sumativa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Automatiza getters y setters
@AllArgsConstructor //Constructor con todos los atributos
@NoArgsConstructor //Constructor sin atributos
public class Usuario {
    private Long id;
    private String nombre;
    private String fhaNacimiento;
    private String email;
    private String rol;
}
