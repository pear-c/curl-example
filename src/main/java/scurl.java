import java.util.HashMap;
import java.util.Map;

public class scurl {
    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser(args);

        // URL
        String urlString = commandParser.getUrl();
        XUrl url = new XUrl(urlString);

        // HTTP 메서드
        String method = commandParser.getMethod();

        // 헤더 설정
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", url.getHost());
        headers.put("User-Agent", "curl/7.79.1");
        headers.put("Accept", "*/*");

        // body 설정
        String body = commandParser.getData();

        // HttpRequest 객체
        HttpRequest httpRequest = new HttpRequest(method, url, headers, body);

        // HttpClient
        HttpClient httpClient = new HttpClient();

        try {
            String response = httpClient.sendRequest(httpRequest);
            System.out.println(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
