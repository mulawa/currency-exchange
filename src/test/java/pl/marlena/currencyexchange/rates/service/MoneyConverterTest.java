package pl.marlena.currencyexchange.rates.service;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.RateProvider;
import pl.marlena.currencyexchange.rates.provider.nbp.NbpJsonRateProvider;
import pl.marlena.currencyexchange.rates.provider.nbp.NbpXmlRateProvider;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoneyConverterTest {

    @Test
    public void shouldCalculateForXmlRateProvider() {
        //given
        NbpXmlRateProvider nbpXmlRateProvider = new NbpXmlRateProvider(new RestTemplate());
        MoneyConverter moneyConverter = new MoneyConverter(nbpXmlRateProvider);

        //when
        Money result = moneyConverter.convertToPLN(Money.of(CurrencyUnit.USD, 20));

        //then
        assertEquals(Money.of(CurrencyUnit.of("PLN"), 70.82), result);
    }



    @Test
    public void shouldCalculateForJsonProvider() {
        //given
        RateProvider rateProvider = new NbpJsonRateProvider(new RestTemplate());
        MoneyConverter moneyConverter = new MoneyConverter(rateProvider);

        //when
        Money result = moneyConverter.convertToPLN(Money.of(CurrencyUnit.USD, 20));

        //then
        assertEquals(Money.of(CurrencyUnit.of("PLN"), 69.50), result);
    }



    @Test
    public void shouldCalculateForMockProvider() {
        //given
        RateProvider rateProvider = new RateProviderMock();
        MoneyConverter moneyConverter = new MoneyConverter(rateProvider);

        //when
        Money result = moneyConverter.convertToPLN(Money.of(CurrencyUnit.USD, 20));

        //then
        assertEquals(Money.of(CurrencyUnit.of("PLN"), 60.00), result);
    }



    class RateProviderMock implements RateProvider{

        @Override
        public List<Rate> getRates() {
            return null;
        }

        @Override
        public Rate getExchangeRate(CurrencyUnit currencyUnit) {
            return Rate.builder().buy(BigDecimal.valueOf(3.0)).build();
        }
    }




}