package pl.marlena.currencyexchange.rates.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.util.Optional;

public class CurrencyUnitDeserializer extends StdDeserializer<CurrencyUnit> {

    /** */
    private static final long serialVersionUID = 1L;

    /** */
    public CurrencyUnitDeserializer() {
        super(CurrencyUnit.class);
    }

    /** */
    @Override
    public CurrencyUnit deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String currency = jp.readValueAs(String.class);
        Optional<CurrencyUnit> unit = CurrencyUnit.registeredCurrencies()
                .stream().filter(currencyUnit -> currencyUnit.getCurrencyCode().equals(currency))
                .findFirst();
        return unit.orElse(CurrencyUnit.USD);
    }
}
