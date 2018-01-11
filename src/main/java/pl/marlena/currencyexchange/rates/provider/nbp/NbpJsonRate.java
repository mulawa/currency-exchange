package pl.marlena.currencyexchange.rates.provider.nbp;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class NbpJsonRate {

    private String currency;
    private String code;
    private String bid;
    private String ask;

}
