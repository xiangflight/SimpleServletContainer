import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/12/11
 */

public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    /**
     * read a set of characters from the socket
     */
    public void parse() {
        StringBuilder request = new StringBuilder(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        System.out.print(request.toString());
        uri = parseUri(request.toString());
    }

    /**
     * 获取 uri
     *
     * @param request http request
     * @return uri of the request
     */
    private String parseUri(String request) {
        return request.split(Constants.BLANK_SPACE)[1];
    }

    public String getUri() {
        return uri;
    }

}
