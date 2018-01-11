package pl.marlena.currencyexchange.rates.service;

import lombok.AllArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.RateProvider;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class MoneyConverter {

    private RateProvider rateProvider;

    public Money convertToPLN (Money source) {
        Rate exchangeRate = rateProvider.getExchangeRate(source.getCurrencyUnit());
        return Money.of(CurrencyUnit.of("PLN"), source.getAmount().multiply(exchangeRate.getBuy()).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}

