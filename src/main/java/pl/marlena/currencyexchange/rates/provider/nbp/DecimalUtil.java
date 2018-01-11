package pl.marlena.currencyexchange.rates.provider.nbp;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

@Slf4j
public class DecimalUtil {


    public static BigDecimal commaSeparatedToBigDecimal(String string) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "##,0000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            return (BigDecimal) decimalFormat.parse(string);
        } catch (ParseException e) {
            log.error("unable to parse decimal string {}", string, e);
        }
        return BigDecimal.ZERO;
    }


    public static BigDecimal dotSeparatedToBigDecimal(String string) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String pattern = "##0.000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            return (BigDecimal) decimalFormat.parse(string);
        } catch (ParseException e) {
            log.error("unable to parse decimal string {}", string, e);
        }
        return BigDecimal.ZERO;
    }
}
