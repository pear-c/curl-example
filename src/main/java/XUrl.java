public class XUrl {
    private String scheme;
    private String host;
    private int port = 80;
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
        return port;
    }

    public String getPath() {
        return path;
    }
}
