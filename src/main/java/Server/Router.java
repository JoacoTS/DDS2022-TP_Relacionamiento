package Server;

import Paginas.PaginasController;
import Repositorios.RepositorioUsuariosDB;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    private static HandlebarsTemplateEngine engine;

    public static void initEngine(){
        Router.engine = new HandlebarsTemplateEngine();
    }

    public static void init(){
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    public static void configure(){
        //RepositorioUsuariosDB repo = new RepositorioUsuariosDB();
        //repo.crearUsuario("admin", "admin");

        PaginasController paginasController = new PaginasController();
        Spark.get("/", paginasController::paginaInicio, Router.engine);

        Spark.get("/:username/:password", paginasController::paginaOperaciones, Router.engine);
    }
}
