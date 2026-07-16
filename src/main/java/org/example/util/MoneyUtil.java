package org.example.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtil {
    public static final int scale=3;
    public static final RoundingMode roundingMode = RoundingMode.HALF_UP;
    public static BigDecimal round(BigDecimal value)
    {
        return value.setScale(scale,roundingMode);
    }
}
