/**
 * Created by mkirwin on 7/24/17.
 */
public class WyvernThread implements Runnable {
    // Static importable object
    public static WyvernThread wyvernThread;

    public void run() {
        System.out.println("WyvernThread.java says hi");
    }
}
