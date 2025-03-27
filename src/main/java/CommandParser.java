import org.apache.commons.cli.*;

public class CommandParser {
    private Options options = new Options();
    private CommandLineParser parser = new DefaultParser();
    private CommandLine cmd;

    public CommandParser(String[] args) {
        initOptions();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("잘못된 형식입니다.");
        }
    }

    private void initOptions() {
        options.addOption(Option.builder("v").desc("verbose, 요청, 응답 헤더를 출력한다.").build());
        options.addOption(Option.builder("H").argName("line").hasArg().desc("임의의 헤더를 서버로 전송한다.").build());
        options.addOption(Option.builder("d").argName("data").hasArg().desc("POST, PUT 등에 데이터를 전송한다.").build());
        options.addOption(Option.builder("X").argName("command").hasArg().desc("사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET").build());
        options.addOption(Option.builder("L").desc("서버의 응답이 30x 계열이면 다음 응답을 따라 간다.").build());
        options.addOption(Option.builder("F").argName("name=content").hasArg().desc("multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.").build());
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

    public String getUrl() {
        String[] args = cmd.getArgs();
        if(args.length < 3) {
            printUsage();
            System.exit(1);
        }
        return args[2];
    }

    private void printUsage() {
        // 도움말 문장을 자동으로 생성합니다
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("scurl [option] url", options);
    }
}
