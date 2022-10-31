package RepoPersonas;

import Personas.Persona;
import Repositorios.RepositorioPersonasDB;
import Usuarios.Usuario;

import java.time.LocalDate;
import java.util.List;

public class RepositorioPersonasTest3 {
  public static void main(String[] args) {

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    List<Persona> personas = repositorioPersonasDB.getPersonas();

    for (Persona persona: personas) {
      System.out.println(persona);
    }

  }
}
