package pl.marlena.currencyexchange.rates.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ConvertFromMainRequest implements Validate{

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String currency;

    @Override
    public void validate(ValidationErrors validationErrors) {
        if(currency.length()> 3)
            validationErrors.add("currency", "has invalid format");
    }

}
