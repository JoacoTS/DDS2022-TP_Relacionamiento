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

    Spark.before("/", AuthMiddleware::verificarSesion);

    Spark.get("/", loginController::inicio, Router.engine);

    Spark.get("/menu_inicio", loginController::inicio, Router.engine);

    Spark.get("/menu_login", loginController::menu_login, Router.engine);

    //Spark.post("/loguearse", loginController::login);

  }
}