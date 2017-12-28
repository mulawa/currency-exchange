package pl.marlena.currencyexchange.rates;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.provider.nbp.NbpRateProvider;
import java.util.List;
import static org.junit.Assert.assertFalse;

public class RateProviderTest {

    @Test
    public void shouldLoadNbpCurrencyRates() {
        //given
        RestTemplate restTemplate = new RestTemplate();
        NbpRateProvider rateProvider = new NbpRateProvider(restTemplate);

        //when
        List<Rate> rates = rateProvider.getRates();

        //then
        assertFalse(rates.isEmpty());
    }


}