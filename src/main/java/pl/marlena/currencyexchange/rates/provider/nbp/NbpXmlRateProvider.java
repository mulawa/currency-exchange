package pl.marlena.currencyexchange.rates.provider.nbp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.RateProvider;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



@Component
@AllArgsConstructor
@Slf4j
public class NbpXmlRateProvider implements RateProvider {

    private static final String NBP_CURRENCY_RATES_URL = "http://www.nbp.pl/kursy/xml/c243z171215.xml";
    private RestTemplate restTemplate;


    @Override
    public List<Rate> getRates() {
        return loadRates().stream()
                .map(nbpXmlRate -> Rate.builder()
                        .currencyFrom(CurrencyUnit.getInstance("PLN"))
                        .currencyTo(CurrencyUnit.getInstance(nbpXmlRate.getCurrencyCode()))
                        .buy(DecimalUtil.commaSeparatedToBigDecimal(nbpXmlRate.getBuy()))
                        .sell(DecimalUtil.commaSeparatedToBigDecimal(nbpXmlRate.getSell()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Rate getExchangeRate(CurrencyUnit currencyTo) {
        return getRates().stream().filter(rate -> rate.getCurrencyTo().equals(currencyTo)).findFirst().get();
    }

    private List<NbpXmlRate> loadRates() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<NbpXmlRates> rates = restTemplate.exchange(NBP_CURRENCY_RATES_URL, HttpMethod.GET, entity, NbpXmlRates.class);
        return rates.getBody().getRates();
    }

}
