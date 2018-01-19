package pl.marlena.currencyexchange.rates.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertDto {

    private String currency;
    private String amount;

    public ConvertDto(String currency, String amount) {
        this.currency = currency;
        this.amount = amount;
    }
}
