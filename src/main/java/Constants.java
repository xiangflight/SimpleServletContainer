import java.io.File;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/12/11
 */

public class Constants {

    /**
     * web server configuration
     */
    public static final String LOCALHOST = "127.0.0.1";
    public static final Integer PORT = 8080;

    /**
     * web root, where static files reside.
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src/main/resources";
    /**
     * shutdown command
     */
    public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    public static final String BLANK_SPACE = " ";

}
