package server.runnable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer implements Runnable {
    private static final int DEFAULT_PORT = 12345;
    private final int port;
    private final ServerSocket serverSocket;

    public HttpServer() {
        this(DEFAULT_PORT);
    }

    public HttpServer(int port) {
        if(port <= 0) {
            throw new IllegalArgumentException();
        }

        this.port = port;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            System.out.println("[Server] 클라이언트 연결 대기중..");

            try (Socket socket = serverSocket.accept();
                 BufferedReader clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter clientOut = new PrintWriter(socket.getOutputStream())) {

                System.out.println("[Server] 클라이언트 연결됨: " + socket.getInetAddress());

                String line;
                while((line = clientIn.readLine()) != null) {
                    System.out.println("[Server] 메시지를 받았습니다: " + line);

                    clientOut.println(line);
                    clientOut.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
