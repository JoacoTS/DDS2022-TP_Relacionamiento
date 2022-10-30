package Controllers;

import Personas.Delegacion;
import Personas.Persona;
import Repositorios.RepositorioPersonasDB;
import Usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuController {

    public ModelAndView menuUsuario(Request request, Response response){
        HashMap<String, String> params = new HashMap<>();

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        return new ModelAndView(params, "MenuUsuario.hbs");
    }

    public ModelAndView menuAutorizarUsuario(Request request, Response response){
        HashMap<String, String> params = new HashMap<>();

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        return new ModelAndView(params, "AutorizarUsuario.hbs");
    }

    public ModelAndView autorizarUsuario(Request request, Response response){

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        String usernameDelegador = request.session().attribute("usuario");
        String usernameDelegado = request.queryParams("usuario_autorizado");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();

        repositorioPersonasDB.generarDelegacion(usernameDelegador, usernameDelegado);

        HashMap<String, String> params = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView(params, "MenuUsuario.hbs");
        return modelAndView;
    }

    public ModelAndView menuSolicitudesAutorizacion(Request request, Response response){

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        HashMap<String, Object> params = new HashMap<>();

        String usuario = request.session().attribute("usuario");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona personaDelegada = repositorioPersonasDB.mostrarPersonaDelegacion(usuario);

        Usuario usuarioDelegado = personaDelegada.getUsuario();
        Delegacion delegacion = repositorioPersonasDB.mostrarDelegacion(usuario);

        params.put("solicitud", usuarioDelegado);
        params.put("delegacion", delegacion);
        return new ModelAndView(params, "SolicitudesAutorizacion.hbs");
    }

    public ModelAndView aceptarAutorizacion(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        String usuarioAceptado = request.params("username");
        //TODO: aceptar autorizacion

        //TODO: volver a buscar solicitudes
        List<Usuario> solicitudes = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();

        return new ModelAndView(params, "SolicitudesAutorizacion.hbs");
    }

    public ModelAndView rechazarAutorizacion(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        String usuarioAceptado = request.params("username");
        //TODO: aceptar autorizacion

        //TODO: volver a buscar solicitudes
        List<Usuario> solicitudes = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();

        return new ModelAndView(params, "SolicitudesAutorizacion.hbs");
    }
}
