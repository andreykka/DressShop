import config.Config;

/**
 * Created by gandy on 22.10.14.
 *
 */
public class testConfig {

    public static void main(String[] args) {

        String msg = "gandy";
        System.out.println(Config.getPasswordHash(msg));

        msg = "andyg";
        System.out.println(Config.getPasswordHash(msg));
    }
}
