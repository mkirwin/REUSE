/**
 * Created 17 July 2017 to write example socket program
 */

import java.net.*;
import java.io.*;
public class Test {
    public static void main(String args[]) {
        // Register service on port 1254
        ServerSocket s = new ServerSocket(1254);
        Socket s1 = s.accept(); // Wait and accept a connection
    }
}