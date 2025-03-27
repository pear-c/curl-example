import org.apache.commons.cli.*;

public class CommandParser {
    private CommandLine cmd;

    public CommandParser(String[] args) {
        Options options = new Options();

        options.addOption("v", false, "verbose, 요청, 응답 헤더를 출력한다.");
        options.addOption("H", true, "임의의 헤더를 서버로 전송한다.");
        options.addOption("d", true, "POST, PUT 등에 데이터를 전송한다.");
        options.addOption("X", true, "사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET");
        options.addOption("L", false, "서버의 응답이 30x 계열이면 다음 응답을 따라 간다.");
        options.addOption("F", true, "multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.");

        CommandLineParser parser = new DefaultParser();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("명령어 파싱 오류!");
            System.out.println("Usage: scurl [option] url");
            System.out.println(options.getOptions());
        }
    }

    public String getMethod() {
        return cmd.getOptionValue("X", "GET");
    }

    public String getHeader() {
        return cmd.getOptionValue("H", "");
    }

    public String getData() {
        return cmd.getOptionValue("d", "");
    }

    public boolean isVerbose() {
        return cmd.hasOption("v");
    }

    public boolean followRedirects() {
        return cmd.hasOption("L");
    }

    public String getFile() {
        return cmd.getOptionValue("F", "");
    }
}
