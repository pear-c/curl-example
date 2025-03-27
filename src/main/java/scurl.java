import java.io.*;
import java.net.Socket;

public class scurl {

    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser(args);

        String url = commandParser.getUrl();
        String method = commandParser.getMethod();
        String header = commandParser.getHeader();
        String data = commandParser.getData();

        try {
            Socket socket = new Socket("httpbin.org", 80);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream();

            String requestString = "GET /get HTTP/1.1\r\n" +
                    "Host: httpbin.org\r\n" +
                    "\r\n";
            out.write(requestString.getBytes());
            out.flush();

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
