import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/12/11
 */

public class Response {

    private Request request;
    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        StringBuilder sb = new StringBuilder();
        Path path = Paths.get(Constants.WEB_ROOT, request.getUri());
        if (Files.exists(path) && !Files.isDirectory(path)) {
            try {
                String content = String.join("\r\n", Files.readAllLines(path));
                sb.append("HTTP/1.1 200 OK\r\n");
                sb.append("Content-Type: text/html;charset=UTF-8\r\n");
                sb.append("Content-Length: ").append(content.length()).append("\r\n");
                sb.append("\r\n");
                sb.append(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            sb.append("HTTP/1.1 404 Not Found\r\n");
            sb.append("Content-Type: text/html\r\n");
            sb.append("Content-Length: 30\r\n");
            sb.append("\r\n");
            sb.append("<h1>Sorry, File Not Found</h1>");
        }
        try {
            output.write(sb.toString().getBytes());
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
