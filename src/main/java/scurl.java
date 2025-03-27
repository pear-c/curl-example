import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class scurl {

    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser(args);



        String protocol = "http://";
        int port = 80;
        String host = "httpbin.org";

        String method = "GET";
        String path = "/get";
        String version = "HTTP/1.1";

        String startstring = method + " " + path + " " + version;
        String body = "";

        String httpRequestBody = startstring +
                "Host: " + host + "\n" +
                "Accept: */*\n" +
                "\n" +
                body;

        try(Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {

            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
                out.println(httpRequestBody);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void setOptions() {
        Options options = new Options();

        options.addOption("v", false, "verbose, 요청, 응답 헤더를 출력한다.");
        options.addOption("H", true, "임의의 헤더를 서버로 전송한다.");
        options.addOption("d", true, "POST, PUT 등에 데이터를 전송한다.");
        options.addOption("X", true, "사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET");
        options.addOption("L", false, "서버의 응답이 30x 계열이면 다음 응답을 따라 간다.");
        options.addOption("F", true, "multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.");
    }
}
