package pl.marlena.currencyexchange.rates.provider;

import org.joda.money.CurrencyUnit;
import pl.marlena.currencyexchange.rates.Rate;

import java.util.List;

public interface RateProvider {

    List<Rate> getRates();

    Rate getExchangeRate(CurrencyUnit currencyUnit);
}
