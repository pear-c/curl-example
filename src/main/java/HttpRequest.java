import java.util.Map;

public class HttpRequest {
    private String method;
    private XUrl url;
    private Map<String, String> headers;
    private String body;

    public HttpRequest(String method, XUrl url, Map<String, String> headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public XUrl getUrl() {
        return url;
    }

    public String getPath() {
        return url.getPath();
    }

    public String getHtttpVersion() {
        return url.getHttpVersion();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String key) {
        return headers.getOrDefault(key, null);
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder requestString = new StringBuilder();
        requestString.append(method).append(" ").append(getPath()).append(" ").append(getHtttpVersion()).append("\r\n");
        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestString.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
        }
        requestString.append("\r\n").append(body);
        return requestString.toString();
    }
}