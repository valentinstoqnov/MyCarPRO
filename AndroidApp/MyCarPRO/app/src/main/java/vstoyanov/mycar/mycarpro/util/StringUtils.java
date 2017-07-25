package vstoyanov.mycar.mycarpro.util;

public class StringUtils {

    public static boolean checkNotNullOrEmpty(String... input) {
        for (String str : input) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        }

        return true;
    }

}
