/**
 * Created by mkirwin on 7/20/17.
 * http://beginnersbook.com/2013/03/multithreading-in-java/
 */
public class ThreadDemo extends Thread {
    public void run() {
        System.out.println("My thread is in running state.");
    }
    public static void main(String args[]) {
        ThreadDemo obj = new ThreadDemo();
        obj.start();
    }
}
