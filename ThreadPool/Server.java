import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final ExecutorService threadPool;
    public Server(int poolSize){
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket socket){
        try (PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true)) {
            toClient.println("Hello From The Server" + socket.getInetAddress());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int port = 8010;
        int poolSize = 50;
        Server server = new Server(poolSize);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port : " + port);
            serverSocket.setSoTimeout(20000);

            while (true){
                Socket socket = serverSocket.accept();
                server.threadPool.execute(() -> server.handleClient(socket));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            server.threadPool.shutdown();
        }
    }
}
