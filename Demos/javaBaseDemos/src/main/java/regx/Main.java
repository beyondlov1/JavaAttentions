package regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author beyondlov1
 * @date 2019/12/01
 */
public class Main {

    /**
     * 不区分大小写
     */
    private static Pattern pattern = Pattern.compile("(?i)hello");

    public static void main(String[] args) {
        String target = "Hello world";
        Matcher matcher = pattern.matcher(target);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
