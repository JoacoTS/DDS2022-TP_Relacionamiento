package RepoPersonas;

import Personas.Persona;
import Repositorios.RepositorioPersonasDB;
import Repositorios.RepositorioUsuariosDB;
import Usuarios.Usuario;

import java.time.LocalDate;

public class RepositorioPersonasTest {
  public static void main(String[] args) {

    Persona persona = new Persona(LocalDate.now() , "ruta", "1235245", "personaNombre", "personaApellido");

    Usuario user = new Usuario("personaUser","contra1234");

    persona.setUsuario(user);

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    repositorioPersonasDB.agregar(persona);

    repositorioPersonasDB.crearPersona("personaNombre", "personaApellido", "213431351", user, LocalDate.of(2022,5,3));

  }
}
