import org.apache.commons.cli.*;

public class AntOptionExample {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("v", false, "verbose, 요청, 응답 헤더를 출력한다.");
        options.addOption("H", true, "임의의 헤더를 서버로 전송한다.");
        options.addOption("d", true, "POST, PUT 등에 데이터를 전송한다.");
        options.addOption("X", true, "사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET");
        options.addOption("L", false, "서버의 응답이 30x 계열이면 다음 응답을 따라 간다.");
        options.addOption("F", true, "multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            // 기본 값 설정
            String method = cmd.getOptionValue("X", "GET");
            String header = cmd.getOptionValue("H", "");
            String data = cmd.getOptionValue("d", "");
            boolean verbose = cmd.hasOption("v");
            boolean followRedirects = cmd.hasOption("L");

            String[] remainingArgs = cmd.getArgs();
            if(remainingArgs.length == 0) {
                System.out.println("Usage: scurl [option] url");
                return;
            }
            for(String s : remainingArgs) {
                System.out.println(s);
            }

            System.out.println("Method: " + method);
//            System.out.println("URL: " + url);
            if(!header.isEmpty()) System.out.println("Header: " + header);
            if(!data.isEmpty()) System.out.println("Data: " + data);
            System.out.println("Verbose: " + verbose);
            System.out.println("followRedirects: " + followRedirects);

        } catch (ParseException e) {
            System.out.println("명령어 파싱 오류 " + e.getMessage());
        }

    }
}
