package pl.marlena.currencyexchange.rates.provider.nbp;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.Rate;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class NbpXmlRateProviderTest {

    @Test
    public void shouldLoadNbpCurrencyRates() {
        //given
        RestTemplate restTemplate = new RestTemplate();
        NbpXmlRateProvider rateProvider = new NbpXmlRateProvider(restTemplate);

        //when
        List<Rate> rates = rateProvider.getRates();

        //then
        assertFalse(rates.isEmpty());
    }


}