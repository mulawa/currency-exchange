package pl.marlena.currencyexchange.rates.service;

import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.nbp.NbpRateProvider;

import java.math.BigDecimal;

public class CurrencyService {

    private NbpRateProvider nbpRateProvider;

    public BigDecimal convertBuy (BigDecimal amount, String currencyTo) {
       Rate result = nbpRateProvider.getExchangeRate(currencyTo);
        if(result == null)
            return null;
        return amount.multiply(result.getBuy());

    }
}
