package Json;

import JSON.InterpreteJson;
import Repositorios.RepositorioPersonasDB;
import Usuarios.Usuario;

import java.time.LocalDate;

public class JsonDBTest {
  public static void main(String[] args) {

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();

    InterpreteJson.guardarPersonasJson(repositorioPersonasDB.getPersonas());

  }
}
