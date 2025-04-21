package com.project.sumativa.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.*;


@Data //Automatiza getters y setters
@AllArgsConstructor //Constructor con todos los atributos
@NoArgsConstructor //Constructor sin atributos
@Entity //Indica que es una entidad de JPA
@Table(name = "usuarios") //Nombre de la tabla en la base de datos
public class Usuario {

    @Id
    @NotNull(message = "Id no puede ser nulo")
    @Positive(message = "Id debe ser un número positivo entero")
    private Long id;

    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "Nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "Fecha de nacimiento no puede ir vacío")
    @Past(message = "Fecha de nacimiento debe ser una fecha pasada")
    private String fhaNacimiento;

    @NotBlank(message = "Correo no puede estar vacío")
    @Email(message = "Correo no es válido")
    private String email;

    @NotBlank(message = "Contraseña no puede estar vacío")
    @Size(min = 13, max = 20, message = "Rol debe tener entre 8 y 20 caracteres")
    private String rol;
}
