package pl.marlena.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.marlena.currencyexchange.rates.controller.CurrencyUnitDeserializer;
import pl.marlena.currencyexchange.rates.controller.MoneyDeserializer;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Money.class, new MoneyDeserializer());
        module.addDeserializer(CurrencyUnit.class, new CurrencyUnitDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
