/**
 * Created by mkirwin on 7/17/17.
 */

import java.net.*;
import java.io.*;
public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // Register service on port 1254
        ServerSocket s = new ServerSocket(1254);
        System.out.println("Asshole is listening for a client!");
        Socket s1 = s.accept(); // Wait and accept a connection
        // Get a communication stream associated with the socket
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        // Send a string!
        dos.writeUTF("THIS IS SERVER ASSHOLEY\n");
        // Close the connection but not the server socket
        dos.close();
        s1out.close();
        s1.close();
    }
}