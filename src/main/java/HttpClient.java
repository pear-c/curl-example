import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClient {
    private static final String httpVersion = "HTTP/1.1";

    private String method;
    private XUrl xurl;

    public HttpClient(String method, String url) {
        xurl = new XUrl(url);
        this.method = method;
    }

    public void sendRequest() {
        try(Socket socket = new Socket(xurl.getHost(), xurl.getPort());
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {

            String requestLine = method + " " + xurl.getPath() + " " + httpVersion + "\r\n" +
                    "Host: " + xurl.getHost() + "\r\n" +
                    "\r\n";

            out.write(requestLine.getBytes());

            String line;
            boolean flag = false;
            while((line = in.readLine()) != null) {
                if(flag) System.out.println(line);
                if(line.isEmpty()) flag = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
