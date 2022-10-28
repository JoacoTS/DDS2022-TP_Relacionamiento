package Controllers;

import JSON.InterpreteJson;
import Usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

  public ModelAndView inicio(Request request, Response response){
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"Inicio.hbs");
  }

  public ModelAndView menu_login(Request request, Response response){
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"Login.hbs");
  }

  public ModelAndView loguear_usuario(Request request, Response response){
    String nombre_usuario = request.queryParams("usuario");
    String password = request.queryParams("password");

    System.out.println(nombre_usuario);
    System.out.println(password);
    //TODO: Verificar usuario
    //if no coincide -> pagina = "Login.hbs"
    //else -> pagina = "MenuUsuario.hbs"
    String pagina = "Login.hbs";

    HashMap<String, String> params = new HashMap<>();
    params.put("usuario", nombre_usuario);

    return new ModelAndView(params, pagina);
  }

  public ModelAndView registrar_usuario(Request request, Response response){
    Usuario usuario = new Usuario();


    //TODO: Verificar que exista Persona en base de datos
    request.queryParams("Nombre");
    request.queryParams("Apellido");
    request.queryParams("Fecha de nacimiento");
    request.queryParams("Ciudad");
    request.queryParams("Localidad");

    usuario.setUsername(request.queryParams("usuario"));

    //TODO: hashear contrasenia
    String contraHasheada = request.queryParams("password");
    usuario.setContraHasheada(contraHasheada);

    //if persona existe -> response.redirect
    request.session().attribute("usuario", request.queryParams("usuario"));
    response.redirect("/menu_usuario");
    //response.redirect("/menu_usuario/"+request.queryParams("usuario"));
    //else "Login.hbs"
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
