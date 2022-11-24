package Repositorios;


import Usuarios.Admin;
import Usuarios.Excepciones.ContraseniaEsInvalidaException;
import Usuarios.Excepciones.UsuarioException;
import Usuarios.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioUsuariosDB extends Repositorio<Usuario> {

  public RepositorioUsuariosDB() {
    super(new DBHibernate<Usuario>(Usuario.class));
  }


  public Boolean existe(String nombreDeUsuario, String contrasenia){
    return buscarUsuario(nombreDeUsuario, contrasenia) != null;
  }

  public Boolean existe(String nombreDeUsuario){
    return buscarUsuario(nombreDeUsuario) != null;
  }

  public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia){
    return this.dbService.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
  }

  public Usuario buscarUsuario(String nombreDeUsuario){
    return this.dbService.buscar(condicionUsername(nombreDeUsuario));
  }

  private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

    Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

    Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("username"), nombreDeUsuario);
    Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contraHasheada"), contrasenia);

    Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    usuarioQuery.where(condicionExisteUsuario);

    return new BusquedaCondicional(null, usuarioQuery);
  }

  private BusquedaCondicional condicionUsername(String nombreDeUsuario){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

    Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

    Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("username"), nombreDeUsuario);
    //Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    usuarioQuery.where(condicionNombreDeUsuario);

    return new BusquedaCondicional(null, usuarioQuery);
  }


  //////////////////////////////////  INTERFACE


  public Usuario validarLogueoUsuario(String username, String contra){
    RepositorioAdminDB repositorioAdminDB = new RepositorioAdminDB();
    Usuario usuarioConUsername = null;
    if(buscarUsuario(username) != null) usuarioConUsername = buscarUsuario(username);
    if (repositorioAdminDB.buscarAdmin(username) != null) usuarioConUsername = repositorioAdminDB.buscarAdmin(username);

    if(usuarioConUsername == null) return null;

    //Si no encuentra el usuario por username
    if(!existe(username)){
      return null;
    }
    //Si el intento de contrasenia es fallido se devolvera nulo
    // lo pongo en los dos lados por si la contrasenia es correcta pero no paso el tiempo del ultimo acceso
    if(usuarioConUsername.isColision(contra)){
      return usuarioConUsername;
    }
    throw new ContraseniaEsInvalidaException("la contrasenia no es la misma a la del usuario");
  }

  public Usuario crearUsuario(String username, String contra){
    try{

      if(existe(username))
        throw new UsuarioException("porque ya hay un usuario con ese username");

      Usuario usuarioNuevo = new Usuario(username,contra);
      //this.usuarios.add(usuarioNuevo);
      agregar(usuarioNuevo);
      return usuarioNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }

  public Admin crearAdmin(String username, String contra){
    try{

      if(existe(username))
        throw new UsuarioException("porque ya hay un usuario con ese username");

      Admin adminNuevo = new Admin(username,contra);
      //this.usuarios.add(adminNuevo);
      agregar(adminNuevo);
      return adminNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }




}