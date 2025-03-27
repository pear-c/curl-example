import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClient {

    public HttpClient() {

    }

    public String sendRequest(HttpRequest request) {
        StringBuilder response = new StringBuilder();

        XUrl url = request.getUrl();
        String host = url.getHost();
        int port = url.getPort();

        try(Socket socket = new Socket(host, port);
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write(request.toString().getBytes());

            // 1️⃣ 응답 헤더 읽기
            String line;
            int contentLength = 0;
            boolean isHeader = true;

            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");

                // 헤더 끝을 감지 (빈 줄)
                if (line.isEmpty()) {
                    isHeader = false;
                    break;
                }

                // Content-Length 추출
                if (line.toLowerCase().startsWith("content-length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            // 2️⃣ 응답 바디 읽기 (Content-Length 기준)
            char[] bodyBuffer = new char[contentLength];
            int readBytes = in.read(bodyBuffer, 0, contentLength);
            if (readBytes > 0) {
                response.append(new String(bodyBuffer, 0, readBytes));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return response.toString();
    }
}
