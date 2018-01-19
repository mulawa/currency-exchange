package pl.marlena.currencyexchange.rates.controller;


import lombok.AllArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.web.bind.annotation.*;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.service.ConverterResult;
import pl.marlena.currencyexchange.rates.service.MoneyConverter;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/converter")
public class ConverterController {

    MoneyConverter converter;

    @PostMapping("toPLN")
    public ConvertDto convertToPLN() {

        Money result = converter.convertToPLN(Money.of(CurrencyUnit.USD, 7));
        return new ConvertDto(result.getCurrencyUnit().toString(), result.getAmount().toString());
    }

    @PostMapping("toMain")
    public ConverterResult convertToMain(@RequestBody Double amount) {
       return converter.convertToMainCurrency(Money.of(CurrencyUnit.USD, amount));
    }

    @PostMapping("fromMain")
    public ConverterResult convertFromMain(@RequestBody ConvertFromMainRequest request) {
        return converter.convertFromMainCurrency(request);
    }

    @PostMapping("fromMainMoney")
    public ConverterResult convertFromMain(@RequestBody Money amount) {
        return converter.convertFromMainCurrency(amount);
    }


}
