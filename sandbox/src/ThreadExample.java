/**
 * Created by mkirwin on 7/24/17.
 *      1. override run() method
 *      2. start() method executes a call to run()
 */

public class ThreadExample implements Runnable {

    @Override
    public void run() {
        System.out.println("ThreadExample implements Runnable");
    }
}

/*
public class ThreadExample extends Thread {
    private String message;

    public ThreadExample(String message) {
        this.message = message;
    }

    public void run() {
        while(true) {
            System.out.println(message);
        }
    }

    public void start() {
        System.out.println("Starting the run() method:");
        this.run();
    }
}
*/
