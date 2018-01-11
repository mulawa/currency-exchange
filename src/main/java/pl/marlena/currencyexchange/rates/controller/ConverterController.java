package pl.marlena.currencyexchange.rates.controller;


import lombok.AllArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marlena.currencyexchange.rates.service.MoneyConverter;

@RestController
@AllArgsConstructor
@RequestMapping("/converter")
public class ConverterController {

    MoneyConverter converter;

    @PostMapping("toPLN")
    public Money convertToPLN() {

        return converter.convertToPLN(Money.of(CurrencyUnit.USD, 5));

    }
}
