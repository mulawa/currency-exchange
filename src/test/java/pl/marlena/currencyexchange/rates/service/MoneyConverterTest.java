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
import java.math.RoundingMode;
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

    @Test
    public void shouldCalculateFromMainCurrencyMock() {
        //given
        RateProvider rateProvider = new RateProviderMock();
        MoneyConverter moneyConverter = new MoneyConverter(rateProvider);

        //when
        ConverterResult result = moneyConverter.convertFromMainCurrency(Money.of(CurrencyUnit.USD, 90));

        //then
        assertEquals(30, result.getAmount());
    }


    @Test
    public void shouldConvertToMainCurrency() {
        RateProvider rateProvider = new NbpJsonRateProvider(new RestTemplate());
        MoneyConverter moneyConverter = new MoneyConverter(rateProvider);
        ConverterResult result = moneyConverter.convertToMainCurrency(Money.of(CurrencyUnit.USD, 7));
        assertEquals("23.79", result.getConvertedAmount());
    }
    @Test
    public void shouldConvertFromMainCurrency() {
        RateProvider rateProvider = new NbpJsonRateProvider(new RestTemplate());
        MoneyConverter moneyConverter = new MoneyConverter(rateProvider);
        ConverterResult result = moneyConverter.convertFromMainCurrency(Money.of(CurrencyUnit.USD, 100));
        assertEquals("28.85", result.getConvertedAmount());
    }

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

