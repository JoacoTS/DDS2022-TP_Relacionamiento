package Controllers;

import JSON.InterpreteJson;
import Repositorios.RepositorioPersonasDB;
import Repositorios.RepositorioUsuariosDB;
import Usuarios.Admin;
import Usuarios.Excepciones.ContraseniaEsInvalidaException;
import Usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

  public ModelAndView inicio(Request request, Response response){
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"Inicio.hbs");
  }

  public ModelAndView menu_login(Request request, Response response){
    Map<String, Object> parametros = new HashMap<>();
    request.session().attribute("usuario", null);

    return new ModelAndView(parametros,"Login.hbs");
  }

  public ModelAndView loguear_usuario(Request request, Response response){
    String nombreUsuario = request.queryParams("usuario");
    String password = request.queryParams("password");

    String pagina;
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    try{
      Usuario u = repositorioUsuariosDB.validarLogueoUsuario(nombreUsuario, password);

      if(u != null){
        if(u instanceof Admin)
            pagina = "MenuAdmin.hbs";
        else
            pagina = "MenuUsuario.hbs";
        request.session().attribute("usuario", u.getUsername());
      }
      else{
        pagina = "Login.hbs";
      }
    }
    catch (ContraseniaEsInvalidaException e){
      pagina = "Login.hbs";
    }

    HashMap<String, String> params = new HashMap<>();

    return new ModelAndView(params, pagina);
  }

  public ModelAndView registrar_usuario(Request request, Response response){
    Usuario usuario = new Usuario(request.queryParams("usuario"), request.queryParams("password"));

    String nombre = request.queryParams("Nombre");
    String apellido = request.queryParams("Apellido");
    //TODO: guardar fecha
    String fechaNac = request.queryParams("Fecha de nacimiento");
    String dni = request.queryParams("DNI");
    //TODO: guardar localidad y ciudad
    //request.queryParams("Ciudad");
    //request.queryParams("Localidad");
//LocalDate.parse(fechaNac, DateTimeFormatter.ofPattern("DD MM AAAA"))

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    repositorioPersonasDB.crearPersona(nombre, apellido, dni, usuario, LocalDate.now());

    request.session().attribute("usuario", usuario.getUsername());
    //response.redirect("/menu_usuario");

    return new ModelAndView(new HashMap<>(), "Login.hbs");
  }

  public ModelAndView cerrarSesion(Request request, Response response){
    request.session().attribute("usuario", null);
    return new ModelAndView(new HashMap<>(), "Login.hbs");
  }

  /**
  public Response login(Request request, Response response){
    try{
      Map<String, Object> parametros = new HashMap<>();
      RepositorioDeEntidades repositorioDeEntidades = FactoryRepositorioEntidad.get();
      RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();
      GestorDePasswords gestorDePasswords = GestorDePasswords.GetInstance();
      GestorDeUsuarios gestorDeUsuarios = GestorDeUsuarios.GetInstance();
      UsuarioController usuarioController = new UsuarioController();

      String nombreDeUsuario = request.queryParams("usuario");
      String passwordHasheada = gestorDePasswords.hashearPassword(request.queryParams("password"));

      if(repoUsuarios.existe(nombreDeUsuario, passwordHasheada)){
        gestorDeUsuarios.loguearse(nombreDeUsuario, passwordHasheada, repoUsuarios);

        Usuario usuario = repoUsuarios.buscarUsuario(nombreDeUsuario, passwordHasheada);

        request.session(true);
        request.session().attribute("id", usuario.getId());
        usuarioController.asignarUsuarioSiEstaLogueado(request,parametros);
        System.out.println("ID DEL LOGUEO :" + request.session().attribute("id"));

        response.redirect("/menu_logueado");
      }
      else{
        response.redirect("/menu_login");
      }
    }
    catch (Exception e){
      //Funcionalidad disponible solo con persistencia en Base de Datos
      response.redirect("/menu_login");
    }
    finally {
      return response;
    }

  }*/

  public static void ensureUserIsLoggedIn(Request request, Response response){

    if(request.session().attribute("id") == null){

      request.session().attribute("afterLoginRedirect", request.pathInfo());

      response.redirect("/menu_login");

    }

  }

  /*
  public static Usuario getCurrentUser(Request request){

    int user_id = request.session().attribute("id");

    Usuario currentUser = FactoryRepositorioUsuarios.get().buscar(user_id);

    return currentUser;
  }
   */

  public Response logout(Request request, Response response){
    request.session().invalidate();
    response.redirect("/");
    return response;
  }
}
