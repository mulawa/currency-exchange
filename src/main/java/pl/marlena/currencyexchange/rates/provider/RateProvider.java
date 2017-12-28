package pl.marlena.currencyexchange.rates.provider;

import pl.marlena.currencyexchange.rates.Rate;

import java.util.List;

public interface RateProvider {

    List<Rate> getRates();
}
