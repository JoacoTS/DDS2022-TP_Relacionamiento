package Json;

import JSON.InterpreteJson;
import Repositorios.RepositorioPersonasDB;

public class JsonDBTest2 {
  public static void main(String[] args) {

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();


    InterpreteJson.leerPersonasJson("personas.json").forEach(repositorioPersonasDB::agregar);

  }
}
