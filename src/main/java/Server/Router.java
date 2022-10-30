package Server;

import Controllers.*;
import Middleware.AuthMiddleware;
import spark.ResponseTransformer;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import Spark.BooleanHelper;
import Spark.HandlebarsTemplateEngineBuilder;

public class Router {
  private static HandlebarsTemplateEngine engine;

  private static void initEngine() {
    Router.engine = HandlebarsTemplateEngineBuilder
        .create()
        .withDefaultHelpers()
        .withHelper("isTrue", BooleanHelper.isTrue)
        .build();
  }

  public static void init() {
    Router.initEngine();
    Spark.staticFileLocation("/public");
    Router.configure();
  }

  private static void configure(){

    LoginController loginController = new LoginController();
    MenuController menuController = new MenuController();
/*
    Spark.before("/", AuthMiddleware::verificarSesion);
    Spark.get("/", loginController::menu_login, Router.engine);
    Spark.get("/menu_inicio", loginController::inicio, Router.engine);
    Spark.get("/menu_login", loginController::menu_login, Router.engine);
    Spark.get("/menu_usuario/:usuario",menuController::menuUsuario, Router.engine);
    Spark.get("/menu_autorizar_usuario/:usuario", menuController::menuAutorizarUsuario, Router.engine);

    Spark.post("/menu_login", loginController::loguear_usuario, Router.engine);
    Spark.post("/menu_login/r", loginController::registrar_usuario, Router.engine);
    Spark.post("/menu_autorizar_usuario/:usuario", menuController::autorizarUsuario, Router.engine);
*/

    Spark.before("/", AuthMiddleware::verificarSesion);
    Spark.get("/", loginController::menu_login, Router.engine);
    Spark.get("/menu_inicio", loginController::inicio, Router.engine);
    Spark.get("/menu_login", loginController::menu_login, Router.engine);
    Spark.get("/menu_usuario",menuController::menuUsuario, Router.engine);
    Spark.get("/menu_autorizar_usuario", menuController::menuAutorizarUsuario, Router.engine);
    Spark.get("/menu_solicitudes_autorizacion", menuController::menuSolicitudesAutorizacion, Router.engine);
    Spark.get("/aceptar_solicitud/:username", menuController::aceptarAutorizacion, Router.engine);
    Spark.get("/rechazar_solicitud/:username", menuController::rechazarAutorizacion, Router.engine);
    Spark.get("/menu_modificar_datos", menuController::menuModificarDatos, Router.engine);
    Spark.get("/menu_reporte_delegaciones", menuController::mostrarReporteDelegaciones,Router.engine);
    Spark.get("/menu_reporte_personas", menuController::mostrarReportePersonas, Router.engine);
    Spark.get("/cerrar_sesion", loginController::cerrarSesion, Router.engine);

    Spark.post("/menu_login", loginController::loguear_usuario, Router.engine);
    Spark.post("/menu_login/r", loginController::registrar_usuario, Router.engine);
    Spark.post("/menu_autorizar_usuario", menuController::autorizarUsuario, Router.engine);
    Spark.post("/menu_datos_persona",menuController::guardarCambiosPersona,Router.engine);

    //Spark.post("/loguearse", loginController::login);

  }
}