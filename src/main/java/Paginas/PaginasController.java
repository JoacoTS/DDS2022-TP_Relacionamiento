package Paginas;

import Repositorios.Repositorio;
import Repositorios.RepositorioUsuariosDB;
import Usuarios.Admin;
import Usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class PaginasController {
    public ModelAndView paginaInicio(Request request, Response response){
        return new ModelAndView(new HashMap<>(), "pagina.hbs");
    }

    public ModelAndView paginaOperaciones(Request request, Response response){
        String pagina;
        String username = request.queryParams("username");
        String contrasenia = request.queryParams("password");
        Usuario usuario = new RepositorioUsuariosDB().buscarUsuario(username, contrasenia);
        if(usuario instanceof Admin){
            pagina = "operacionesAdmin.hbs";
        }
        else{
            pagina = "operacionesUsuario.hbs";
        }

        return new ModelAndView(new HashMap<>(), pagina);
    }
}