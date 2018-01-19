package pl.marlena.currencyexchange.rates.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConverterResult {

    private String currencyFrom;
    private String currencyTo;
    private String amount;
    private String convertedAmount;
    private String exchangeRate;

    public ConverterResult(String currencyFrom, String currencyTo, String amount, String convertedAmount,
                           String exchangeRate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.exchangeRate = exchangeRate;
    }
}
