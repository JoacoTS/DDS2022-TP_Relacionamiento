package RepoPersonas;

import Personas.Delegacion;
import Repositorios.RepositorioPersonasDB;
import Usuarios.Usuario;

import java.time.LocalDate;
import java.util.List;

public class RepositorioPersonasTest4 {
  public static void main(String[] args) {

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    List<Delegacion> delegaciones = repositorioPersonasDB.getDelegaciones();

    for (Delegacion delegacion : delegaciones) {
      System.out.println(delegacion);
    }

  }
}
