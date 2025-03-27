import java.io.IOException;
import java.net.Socket;

public class HttpClient {
    private static final int DEFAULT_PORT = 80;
    private static final String DEFAULT_HTTP_VERSION = "HTTP/1.1";

    private String url;

    private String protocol;
    private String method;
    private String host;
    private String header;
    private String data;


    public HttpClient(String url) {
        this.url = url;
    }

    static void parsingURL(String url) {


    }



}
