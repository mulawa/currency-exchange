package pl.marlena.currencyexchange.rates.service;

import lombok.AllArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.controller.ConvertFromMainRequest;
import pl.marlena.currencyexchange.rates.provider.RateProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class MoneyConverter {

    public static String MAIN_CURRENCY = "PLN";
    private RateProvider rateProvider;

    public Money convertToPLN (Money source) {
        Rate exchangeRate = rateProvider.getExchangeRate(source.getCurrencyUnit());
        return Money.of(CurrencyUnit.of("PLN"), source.getAmount().multiply(exchangeRate.getBuy()).
                setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public ConverterResult convert(Rate rate, Money amount){
        if(rate.getCurrencyTo().equals(MAIN_CURRENCY)) {
            return convertToMainCurrency(amount);
        } else {
            return convertFromMainCurrency(amount);
        }
    }

    public ConverterResult convertToMainCurrency (Money amount) {
        Rate exchangeRate = rateProvider.getExchangeRate(amount.getCurrencyUnit());
        isExist(exchangeRate);
        BigDecimal convertedAmount = amount.getAmount().multiply(exchangeRate.getBuy()).
                setScale(2, BigDecimal.ROUND_HALF_UP);
        return new ConverterResult(exchangeRate.getCurrencyFrom().toString(), MAIN_CURRENCY,
                amount.getAmount().toString(),
                convertedAmount.toString(), exchangeRate.getBuy().toString());
    }

    public ConverterResult convertFromMainCurrency(Money amount) {
        Rate exchangeRate = rateProvider.getExchangeRate(amount.getCurrencyUnit());
        isExist(exchangeRate);
        BigDecimal convertedAmount = amount.getAmount().divide(exchangeRate.getSell(),
                2, RoundingMode.HALF_UP);
        return new ConverterResult(MAIN_CURRENCY, amount.getCurrencyUnit().toString(),
                amount.getAmount().toString(),
                convertedAmount.toString(), exchangeRate.getSell().toString());
    }

    public ConverterResult convertFromMainCurrency(ConvertFromMainRequest request) {
        Rate exchangeRate = rateProvider.getExchangeRate(CurrencyUnit.of(request.getCurrency()));
        isExist(exchangeRate);
        BigDecimal convertedAmount = request.getAmount().divide(exchangeRate.getSell(),
                2, RoundingMode.HALF_UP);
        return new ConverterResult(MAIN_CURRENCY, request.getCurrency(),
                request.getAmount().toString(),
                convertedAmount.toString(), exchangeRate.getSell().toString());
    }

    private void isExist(Rate rate) {
        if (rate == null)
            throw new NoRateException();
    }
}

