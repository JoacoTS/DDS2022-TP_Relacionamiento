package RepoUsuarios;

import Repositorios.RepositorioUsuariosDB;
import Usuarios.Usuario;

public class RepositorioUsuariosTest {
  public static void main(String[] args) {

    Usuario user = new Usuario("insertUsuario","hola127354");

    //Miembro miembro = new Miembro();

    RepositorioUsuariosDB repositorioUsuarios = new RepositorioUsuariosDB();
    repositorioUsuarios.agregar(user);

    repositorioUsuarios.crearUsuario("insertUsuario2","hola123542");

  }
}
