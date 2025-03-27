import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClient {

    public HttpClient() {

    }

    public void sendRequest(HttpRequest request) {
        XUrl url = request.getUrl();
        String host = url.getHost();
        int port = url.getPort();

        try(Socket socket = new Socket(host, port);
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write(request.toString().getBytes());

            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
