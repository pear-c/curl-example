package server.main;

import server.runnable.HttpServer;

public class ServerMain {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        Thread thread = new Thread(httpServer);
        thread.start();
    }
}
