package client.main;

import client.runnable.HttpClient;

public class ClientMain {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        Thread thread = new Thread(httpClient);
        thread.start();
    }
}
