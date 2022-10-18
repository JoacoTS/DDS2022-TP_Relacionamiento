package Server;

import spark.Spark;

public class Server {
    public static void main(String arg[]){
        Spark.port(9000);
        Router.init();
    }
}
