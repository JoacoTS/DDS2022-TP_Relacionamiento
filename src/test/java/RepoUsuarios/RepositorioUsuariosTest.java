package RepoUsuarios;

import Repositorios.RepositorioUsuariosDB;
import Usuarios.Admin;
import Usuarios.Usuario;

public class RepositorioUsuariosTest {
  public static void main(String[] args) {

    RepositorioUsuariosDB repositorioUsuarios = new RepositorioUsuariosDB();

    repositorioUsuarios.crearAdmin("admin","hola1234");

  }
}
