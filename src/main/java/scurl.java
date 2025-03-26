import java.net.Socket;

public class scurl {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Usage: scurl [option] url\n" +
                    "Options:\n" +
                    "-v                  verbose, 요청, 응답 헤더를 출력한다.\n" +
                    "-H <line>           임의의 헤더를 서버로 전송한다.\n" +
                    "-d <data>           POST, PUT 등에 데이터를 전송한다.\n" +
                    "-X <command>        사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET\n" +
                    "-L                  서버의 응답이 30x 계열이면 다음 응답을 따라 간다.\n" +
                    "-F <name=content>   multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.\n");
            return;
        } else if(args.length == 1) {

        }
    }
}
