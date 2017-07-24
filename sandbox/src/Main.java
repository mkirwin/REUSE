public class Main {

    public static void main(String[] args) {
        ThreadExample babyThread = new ThreadExample();
        new Thread(babyThread).start();
    }
}
