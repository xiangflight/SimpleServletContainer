import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/12/11
 */

public class HttpServer {

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        int port = Constants.PORT;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName(Constants.LOCALHOST));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        boolean shutdown = false;

        // Loop waiting for a request
        while (!shutdown) {
            Socket socket;
            InputStream input;
            OutputStream output;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create Request object and parse
                Request request = new Request(input);
                request.parse();

                // create Response object
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                // close the socket
                socket.close();

                // check if the previous URI is a shutdown command
                shutdown = Objects.equals(request.getUri(), Constants.SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
