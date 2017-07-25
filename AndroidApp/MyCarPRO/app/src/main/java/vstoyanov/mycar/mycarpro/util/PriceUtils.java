package vstoyanov.mycar.mycarpro.util;

import java.math.BigDecimal;

public class PriceUtils {

    public static final BigDecimal LOWEST_UNIT = new BigDecimal(100);

    public static String longToString(long value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.divide(LOWEST_UNIT, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    public static long stringToLong(String string) throws NumberFormatException{
        return new BigDecimal(string).multiply(LOWEST_UNIT).longValue();
    }
}
