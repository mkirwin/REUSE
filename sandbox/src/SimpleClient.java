import java.net.*;
import java.io.*;

/**
 * Created by mkirwin on 7/17/17.
 */
public class SimpleClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Program has made it to client");
        // Open your connection to a server, at port 1254
        Socket s1 = new Socket("localhost", 1254);
        // Get an input file handle from the socket and read the input
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String str = new String(dis.readUTF());
        System.out.println(str);
        // When done, just close the connection and exit
        dis.close();
        s1In.close();
        s1.close();
    }
}
