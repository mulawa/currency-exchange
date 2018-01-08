package pl.marlena.currencyexchange.rates.provider.nbp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.RateProvider;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class NbpRateProvider implements RateProvider {

    private static final String NBP_CURRENCY_RATES_URL ="http://api.nbp.pl/api/exchangerates/tables/c/today";//"http://www.nbp.pl/kursy/xml/c243z171215.xml";
    private RestTemplate restTemplate;


    @Override
    public List<Rate> getRates() {
        return loadRates().stream()
                .map(nbpRate -> Rate.builder()
                        .currencyFrom(CurrencyUnit.getInstance("PLN"))
                        .currencyTo(CurrencyUnit.getInstance(nbpRate.getCurrencyCode()))
                        .buy(toBigDecimal(nbpRate.getBuy()))
                        .sell(toBigDecimal(nbpRate.getSell()))
                        .build())
                .collect(Collectors.toList());
    }

    public Rate getExchangeRate(String currencyTo) {
        return getRates().stream().filter(rate -> rate.getCurrencyTo().equals(currencyTo)).findFirst().get();
    }
    private List<NbpRate> loadRates() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<NbpRates> rates = restTemplate.exchange(NBP_CURRENCY_RATES_URL, HttpMethod.GET, entity, NbpRates.class);
        return rates.getBody().getRates();
    }

    private BigDecimal toBigDecimal(String string) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "##,0000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            return (BigDecimal) decimalFormat.parse(string);
        } catch (ParseException e) {
            log.error("unable to parse decimal string {}", string, e);
        }
        return BigDecimal.ZERO;
    }
}
