package com.project.sumativa.service;

import com.project.sumativa.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario(1L, "Hernesto Jaramillo Gonzalez", "$2y$10$ooQB8CUtS4G1QjUQoJytne.8jrxoCS8jrKY7Omh7sPSUUAhCNKn0.", "hernestitojargon@hotmail.com", "conductor standard"));
        usuarios.add(new Usuario(2L, "Jaime Zamorano Jara", "$2y$10$fodpxax6J8BtfbSvtIrOFeBAry6RunIRIMGbSRvxupQjnzi4/MTHG", "dogloverjaime@gmail.com", "conductor pet-friendly"));
        usuarios.add(new Usuario(3L, "Miguel Heinz Matta", "$2y$10$pbbP6eBqdIGTBIaQHfAegOYyvwnHRfIpOUTP6Dgk..6G/RBmBs3Wi", "heinzking99@gmail.com", "conductor pet-friendly"));
        usuarios.add(new Usuario(4L, "Estefania Medel Rodriguez", "$2y$10$jMDVBeoqSKRk2wFP3/j9TOzyNuBycKfZ7Blyhhyk0nnt/Sl8gOxOe", "e.medelrodrig@gmail.com", "conductor pet-friendly"));
        usuarios.add(new Usuario(5L, "Mauricio Carrasco Jimenez", "$2y$10$9hiossHuYmTGKELq.dRhUeNMqJyos8jdPepkO1YS8.HvTW.TYjlI2", "mau.colo1991@gmail.com", "dueño de mascota"));
        usuarios.add(new Usuario(6L, "Matias Torrejon Mendez", "$2y$10$vR3dGBqqvTMGycbeSl/TCO84MCYfXlmOFgFL8d4wOybNJhOaolOly", "matiexe.99@gmail.com", "dueño de mascota"));
        usuarios.add(new Usuario(7L, "Maria San Martin Faundez", "$2y$10$1rQNHVn69dfOLJ.ol5SMF.7Nq3K76zXtYWQbN944EQuyaKbsp.x8i", "nihongobonito03@gmail.com", "dueño de mascota"));
        usuarios.add(new Usuario(8L, "Francesco Sepulveda Farias", "$2y$10$kmwKfhgvPQCg2HwWOj8pBekTOW1tpuna3H896Ept71nwQlG4ugV0e", "fran.sepulveda@hotmail.com", "dueño de mascota"));
        usuarios.add(new Usuario(9L, "Ana Sandoval Martinez", "$2y$10$qht8tAcVZINECTzNH4OdgOlbHDJ8Vf8eeB576yoAM6YddIS/QTCYi", "ankorner@gmail.com", "dueño de mascota"));
    }
    
    public List<Usuario> getAllUsuarios() {
        return usuarios;
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
