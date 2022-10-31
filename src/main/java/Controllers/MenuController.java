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
import java.util.Optional;

public class MenuController {

    public ModelAndView menuUsuario(Request request, Response response){
        HashMap<String, String> params = new HashMap<>();

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        return new ModelAndView(params, "MenuUsuario.hbs");
    }

    public ModelAndView menuAutorizarUsuario(Request request, Response response){
        HashMap<String, Object> params = new HashMap<>();

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        params.put("se_realizo_pedido_operacion", false);
        return new ModelAndView(params, "AutorizarUsuario.hbs");
    }

    public ModelAndView autorizarUsuario(Request request, Response response){

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        String usernameDelegador = request.session().attribute("usuario");
        String usernameDelegado = request.queryParams("usuario_autorizado");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();

        boolean resultadoOperacion = repositorioPersonasDB.generarDelegacion(usernameDelegador, usernameDelegado);

        HashMap<String, Boolean> params = new HashMap<>();
        params.put("se_realizo_pedido_operacion", true);
        params.put("resultado_operacion", resultadoOperacion);
        return new ModelAndView(params, "AutorizarUsuario.hbs");
    }


    public ModelAndView menuSolicitudesAutorizacion(Request request, Response response){

        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        HashMap<String, Object> params = new HashMap<>();

        String usuario = request.session().attribute("usuario");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Optional<Persona> personaDelegada = repositorioPersonasDB.mostrarPersonaDelegacion(usuario);


        Optional<Usuario> usuarioDelegado = personaDelegada.map(Persona::getUsuario);

        Optional<Delegacion> delegacion = repositorioPersonasDB.mostrarDelegacion(usuario);

        usuarioDelegado.ifPresent(value -> params.put("solicitud", value));
        delegacion.ifPresent(value -> params.put("delegacion", value));
        delegacion.ifPresent(value -> params.put("aceptada", value.isAceptada()));

        if(delegacion.isPresent() && usuarioDelegado.isPresent())
            params.put("se_realizo_pedido_operacion", true);
        else
            params.put("se_realizo_pedido_operacion", false);
        return new ModelAndView(params, "SolicitudesAutorizacion.hbs");
    }

    public ModelAndView aceptarAutorizacion(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        String usuarioAceptado = request.params("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        repositorioPersonasDB.cambiarAutorizacionDelegacion(usuarioAceptado,true);

        HashMap<String, Object> params = new HashMap<>();
        return new ModelAndView(params, "MenuUsuario.hbs");
    }

    public ModelAndView rechazarAutorizacion(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        String usuarioAceptado = request.params("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        repositorioPersonasDB.cambiarAutorizacionDelegacion(usuarioAceptado,false);

        HashMap<String, Object> params = new HashMap<>();

        return new ModelAndView(params, "MenuUsuario.hbs");
    }

    public ModelAndView menuModificarDatos(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        String usuario = request.session().attribute("usuario");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(usuario);

        HashMap<String, Object> params = new HashMap<>();

        params.put("nombre",persona.getNombre());
        params.put("apellido", persona.getApellido());
        params.put("dni", persona.getDni());
        params.put("se_realizo_pedido_operacion", false);

        return new ModelAndView(params, "DatosPersona.hbs");
    }

    public ModelAndView guardarCambiosPersona(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        HashMap<String, Object> params = new HashMap<>();

        String nuevoNombre = request.queryParams("Nombre");
        String nuevoApellido = request.queryParams("Apellido");
        String nuevoDni = request.queryParams("DNI");


        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(request.session().attribute("usuario"));
        if(persona != null){
            persona.setNombre(nuevoNombre);
            persona.setApellido(nuevoApellido);
            persona.setDni(nuevoDni);

            repositorioPersonasDB.modificar(persona);

            params.put("nombre",persona.getNombre());
            params.put("apellido", persona.getApellido());
            params.put("dni", persona.getDni());
            params.put("resultado_operacion", true);
        }
        else{
            params.put("resultado_operacion", false);
        }

        params.put("se_realizo_pedido_operacion", true);

        return new ModelAndView(params, "DatosPersona.hbs");
    }

    public ModelAndView mostrarReportePersonas(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        List<Persona> personas = repositorioPersonasDB.getPersonas();

        HashMap<String, Object> params = new HashMap<>();

        params.put("personas", personas);
        return new ModelAndView(params, "ReportePersonas.hbs");
    }

    public ModelAndView mostrarReporteDelegaciones(Request request, Response response){
        if(request.session().attribute("usuario") == null)
            response.redirect("/menu_login");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        List<Delegacion> delegaciones = repositorioPersonasDB.getDelegaciones();

        HashMap<String, Object> params = new HashMap<>();

        params.put("delegaciones", delegaciones);
        return new ModelAndView(params, "ReportePersonas.hbs");
    }
}
