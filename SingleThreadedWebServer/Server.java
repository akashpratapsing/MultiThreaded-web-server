import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket sc;
    PrintWriter toClient;
    BufferedReader fromClient;
    Socket acceptedConnection;
    public void run() throws IOException {
        int port = 8010;
        sc = new ServerSocket(port);
        sc.setSoTimeout(10000);

        while (true){
            try {
                System.out.println("Server is listening on :" + port);
                acceptedConnection = sc.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());
                toClient = new PrintWriter(acceptedConnection.getOutputStream());
                fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello From the server");
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            }
        }
    }
    public static void main(String[] args) {

        Server server = new Server();
        try {
            server.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
