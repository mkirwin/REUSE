/**
 * Created by mkirwin on 7/20/17.
 * http://beginnersbook.com/2013/03/multithreading-in-java/
 * TODO: Get working example...
 */
public class Count extends Thread {
    Count() {
        super("My Extending Thread");
        System.out.println("My thread created " + this);
        start();
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Printing count: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) { System.out.println("My thread interrupted"); }
        System.out.println("My thread run is over");
    }

    public static void main(String args[]) {
        Count cnt = new Count();
        try {
            while(cnt.isAlive()) {
                System.out.println("Main thread will be alive till the child thread is live");
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) { System.out.println("Main thread interrupted"); }
        System.out.println("Main thread's run is over");
    }
}
