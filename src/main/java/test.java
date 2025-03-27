public class test {
    public static void main(String[] args) {
        CommandParser cp = new CommandParser(args);

        System.out.println(cp.getMethod());
    }
}
