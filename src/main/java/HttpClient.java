import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClient {
    boolean verbose;

    public HttpClient(boolean verbose) {
        this.verbose = verbose;
    }

    public String sendRequest(HttpRequest request) {
        StringBuilder headerSb = new StringBuilder();  // Header
        StringBuilder bodySb = new StringBuilder();    // Body

        XUrl url = request.getUrl();
        String host = url.getHost();
        int port = url.getPort();

        try(Socket socket = new Socket(host, port);
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            if(verbose) {
                System.out.println("* Trying " + socket.getLocalAddress().toString().substring(1) + ":" + port + "...");
                System.out.println("* Connected to " + host + " (" + socket.getLocalAddress().toString().substring(1) + ") port " + port + " (#0)");
                System.out.println("> " + request.toString().replaceAll("\n", "\n> "));
            }

            out.write(request.toString().getBytes());
            out.flush();

            String line;
            int contentLength = -1;

            while((line = in.readLine()) != null) {
                headerSb.append(line).append("\n"); // 헤더 부분

                if(line.isEmpty()) {
                    break;
                }

                if(line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }

                if(verbose) {
                    System.out.println("< " + line);
                }
            }

            if(contentLength > 0) {
                // Body 부분
                char[] body = new char[contentLength];
                in.read(body, 0, contentLength);
                bodySb.append(new String(body));

                if(verbose) {
                    System.out.println("< " + new String(body));
                }
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // -v 옵션 있을 때는 헤더 + body 둘다 출력
        if(verbose) {
            return headerSb.append(bodySb).toString();
        } else {
            return bodySb.toString();
        }
    }
}
