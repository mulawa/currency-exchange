package pl.marlena.currencyexchange.rates.provider.nbp;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.Rate;

import java.util.List;

import static org.junit.Assert.*;

public class NbpJsonRateProviderTest {

    @Test
    public void shouldLoadNbpCurrencyRates() {
        //given
        RestTemplate restTemplate = new RestTemplate();
        NbpJsonRateProvider rateProvider = new NbpJsonRateProvider(restTemplate);

        //when
        List<Rate> rates = rateProvider.getRates();

        //then
        assertFalse(rates.isEmpty());
    }

}