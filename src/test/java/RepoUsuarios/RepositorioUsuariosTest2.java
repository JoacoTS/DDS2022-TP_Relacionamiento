package RepoUsuarios;

import Repositorios.RepositorioUsuariosDB;
import Usuarios.Usuario;

public class RepositorioUsuariosTest2 {
  public static void main(String[] args) {

    RepositorioUsuariosDB repositorioUsuarios = new RepositorioUsuariosDB();

    Usuario usuario = repositorioUsuarios.validarLogueoUsuario("admin","hola1234");

    System.out.println(usuario);

  }
}
