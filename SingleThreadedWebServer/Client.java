import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    Socket socket;
    PrintWriter toSocket;
    BufferedReader fromSocket;
    public void run() throws IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");


        try {
           socket = new Socket(address, port);
            toSocket = new PrintWriter(socket.getOutputStream());
            fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toSocket.println("Hello from the client ");
            String line = fromSocket.readLine();
            System.out.println("Socket says " + line);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            toSocket.close();
            fromSocket.close();
            socket.close();
        }
    }

    public static void main(String[] args) {
         Client client = new Client();
         try {
             client.run();
         }catch (Exception e){
             e.printStackTrace();
         }
    }
}
