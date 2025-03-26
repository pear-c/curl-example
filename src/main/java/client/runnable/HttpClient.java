package client.runnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpClient implements Runnable {
    private final static String DEFAULT_SERVER_ADDRESS = "localhost";
    private final static int DEFAULT_PORT = 12345;

    private final String serverAddress;
    private final int serverPort;

    private final Socket clientSocket;

    public HttpClient() {
        this(DEFAULT_SERVER_ADDRESS, DEFAULT_PORT);
    }

    public HttpClient(String serverAddress, int serverPort) {
        if(serverAddress.isEmpty() || serverPort <= 0) {
            throw new IllegalArgumentException();
        }

        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        try {
            clientSocket = new Socket(this.serverAddress, this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try(PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.print("send-message: ");
            String line;

            while((line = clientIn.readLine()) != null) {
                clientOut.println(line);
                System.out.println("send-message: ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
