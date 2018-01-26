package geny.common.constant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by dat on 1/23/2018.
 */
public class Constants {
    // loyalty points
    public static final int ONE_THOUSAND_POINTS = 1000;
    public static final int TWO_THOUSAND_POINTS = 2000;
    public static final int FIVE_THOUSAND_POINTS = 5000;
    public static final int TEN_THOUSAND_POINTS = 10000;
    public static final int ONE_HUNDRED_THOUSAND_POINTS = 100000;

    // transaction amount (VND)
    public static final BigDecimal FIVE_THOUSAND_VND = new BigDecimal(5000);
    public static final BigDecimal ONE_MILLION_VND = new BigDecimal(1000000);

    // campaign dates
    public static LocalDate LAUNCHING_START_DATE = LocalDate.of(2018, Month.APRIL, 1);
    public static LocalDate LAUNCHING_END_DATE = LocalDate.of(2018, Month.AUGUST, 31);
    public static LocalDate SUMMER_START_DATE = LocalDate.of(2018, Month.JUNE, 1);
    public static LocalDate SUMMER_END_DATE = LocalDate.of(2018, Month.AUGUST, 31);
}
