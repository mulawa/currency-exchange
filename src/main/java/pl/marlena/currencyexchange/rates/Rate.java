package pl.marlena.currencyexchange.rates;

import lombok.Builder;
import lombok.Value;
import org.joda.money.CurrencyUnit;

import java.math.BigDecimal;

@Value
@Builder
public class Rate {
    private CurrencyUnit currencyFrom;
    private CurrencyUnit currencyTo;
    private BigDecimal buy;
    private BigDecimal sell;
}
