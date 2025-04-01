package com.project.sumativa.service;

import com.project.sumativa.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario(1L, "Hernesto Jaramillo Gonzalez", "1993-02-26", "hernestitojargon@hotmail.com", "Conductor Pet-Friendly"));
        usuarios.add(new Usuario(2L, "Jaime Zamorano Jara",         "1982-09-16", "dogloverjaime@gmail.com", "Conductor Pet-Friendly"));
        usuarios.add(new Usuario(3L, "Miguel Heinz Matta",          "1990-12-07", "heinzking99@gmail.com", "Conductor Standard"));
        usuarios.add(new Usuario(4L, "Estefania Medel Rodriguez",   "1987-10-30", "e.medelrodrig@gmail.com", "Dueño c/Mascota"));
        usuarios.add(new Usuario(5L, "Mauricio Carrasco Jimenez",   "1998-11-29", "mau.colo1991@gmail.com", "Dueño c/Mascota"));
        usuarios.add(new Usuario(6L, "Matias Torrejon Mendez",      "1999-07-16", "matiexe.99@gmail.com", "Dueño c/Mascota"));
        usuarios.add(new Usuario(7L, "Maria San Martin Faundez",    "1994-09-19", "nihongobonito03@gmail.com", "Dueño c/Mascota"));
        usuarios.add(new Usuario(8L, "Francesco Sepulveda Farias",  "2001-04-11", "fran.sepulveda@hotmail.com", "Dueño s/Mascota"));
        usuarios.add(new Usuario(9L, "Ana Sandoval Martinez",       "2002-07-09", "ankorner@gmail.com", "Dueño s/Mascota"));
    }
    
    public List<Usuario> getAllUsuarios() {
        return usuarios;
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

}
