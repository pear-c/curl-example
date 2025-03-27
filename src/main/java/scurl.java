public class scurl {
    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser(args);
        HttpClient httpClient = new HttpClient(commandParser.getMethod(), commandParser.getUrl());
        httpClient.sendRequest();
    }
}
