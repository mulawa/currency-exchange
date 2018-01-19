package pl.marlena.currencyexchange.rates.provider.nbp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.RateProvider;

import java.util.List;
import java.util.stream.Collectors;

import static pl.marlena.currencyexchange.rates.provider.nbp.DecimalUtil.dotSeparatedToBigDecimal;


@Primary
@Component
@AllArgsConstructor
@Slf4j
public class NbpJsonRateProvider  implements RateProvider {

    private static final String NBP_CURRENCY_RATES_URL = "http://api.nbp.pl/api/exchangerates/tables/c/last";
    private RestTemplate restTemplate;

    @Override
    public List<Rate> getRates() {
        ResponseEntity<NbpJsonRates[]> rates = restTemplate.getForEntity(NBP_CURRENCY_RATES_URL, NbpJsonRates[].class);
        return rates.getBody()[0].getRates().stream().map(rate -> Rate.builder()
                .currencyFrom(CurrencyUnit.getInstance(rate.getCode()))
                .currencyTo(CurrencyUnit.getInstance(rate.getCode()))
                .buy(dotSeparatedToBigDecimal(rate.getBid()))
                .sell(dotSeparatedToBigDecimal(rate.getAsk()))
                .build())
                .collect(Collectors.toList());
    }

    public Rate getExchangeRate(CurrencyUnit currencyTo) {
        return getRates().stream().filter(rate -> rate.getCurrencyTo().equals(currencyTo)).findFirst().get();
    }



}
