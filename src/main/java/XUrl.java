public class XUrl {
    private static final int DEFAULT_PORT = 80;
    private static final String DEFAULT_HTTP_VERSION = "HTTP/1.1";

    private String scheme;
    private String host;
    private String path;

    public XUrl(String url) {
        parseURL(url);
    }

    private void parseURL(String url) {
        // 스킴
        int schemeIndex = url.indexOf("://");
        this.scheme = url.substring(0, schemeIndex);
        url = url.substring(schemeIndex + 3);

        // Host
        int hostIndex = url.indexOf("/");
        this.host = url.substring(0, hostIndex);
        url = url.substring(hostIndex);

        // Path
        this.path = url;
    }

    public String getScheme() {
        return scheme;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return DEFAULT_PORT;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return DEFAULT_HTTP_VERSION;
    }
}
