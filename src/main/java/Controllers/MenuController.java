package Controllers;

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

        //TODO: Agregar solicitud con usuario
        String usuarioSolicitador = request.session().attribute("usuario");
        String usuarioAutorizado = request.params("usuario_autorizado");

        HashMap<String, String> params = new HashMap<>();
        return new ModelAndView(params, "AutorizarUsuario.hbs");
    }

    public ModelAndView menuSolicitudesAutorizacion(Request request, Response response){

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        HashMap<String, Object> params = new HashMap<>();
        //TODO: buscar solicitudes de usuario
        String usuario = request.session().attribute("usuario");

        Usuario usuario1 = new Usuario("a","asgasdfasgasvse");
        Usuario usuario2 = new Usuario("b", "asgasdfasgasvse");

        List<Usuario> solicitudes = new ArrayList<>();
        solicitudes.add(usuario1);
        solicitudes.add(usuario2);

        params.put("solicitudes", solicitudes);
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
