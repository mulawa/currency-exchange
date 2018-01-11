package pl.marlena.currencyexchange.rates.provider.nbp;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class NbpJsonRates {

    private String table;
    private String no;
    private String tradingDate;
    private String effectiveDate;
    private List<NbpJsonRate> rates;

}
