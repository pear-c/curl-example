public class test {
    public static void main(String[] args) {
        CommandParser parser = new CommandParser(args);

        System.out.println(parser.getMethod());
        System.out.println(parser.getUrl());
    }
}
