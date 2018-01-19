package pl.marlena.currencyexchange.rates.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateDto {

    private String currency;
    private String buy;
    private String sell;

    public RateDto(String currency, String buy, String sell) {
        this.currency = currency;
        this.buy = buy;
        this.sell = sell;
    }
}
