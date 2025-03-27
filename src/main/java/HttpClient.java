import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class HttpClient {
    private XUrl xurl;
    private String method;

    private String requestLine;
    private Map<String, String> headers;
    private Map<String, String> body;


    public HttpClient(String method, String url) {
        xurl = new XUrl(url);
        this.method = method;

        requestLine = this.method + " " + xurl.getPath() + " " + xurl.getHttpVersion() + "\r\n" +
                "Host: " + xurl.getHost() + "\r\n" +
                "User-Agent: curl/7.79.1\r\n" +
                "Accept: */*\r\n" +
                "\r\n";
    }

    public void sendRequest() {
        try(Socket socket = new Socket(xurl.getHost(), xurl.getPort());
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {

            String requestLine = method + " " + xurl.getPath() + " " + xurl.getHttpVersion() + "\r\n" +
                    "Host: " + xurl.getHost() + "\r\n" +
                    "User-Agent: curl/7.79.1\r\n" +
                    "Accept: */*\r\n" +
                    "\r\n";

            out.write(requestLine.getBytes());

            printDefaultResponse(socket, in);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printDefaultResponse(Socket socket, BufferedReader in) {
        try {
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
