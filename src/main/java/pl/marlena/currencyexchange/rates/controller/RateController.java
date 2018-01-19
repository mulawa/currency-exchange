package pl.marlena.currencyexchange.rates.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.RateProvider;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/rates")
public class RateController {


    private RateProvider rateProvider;

    @GetMapping
    public List<RateDto> get() {
        List<Rate> rates = rateProvider.getRates();

        return rates.stream()
                .map(rate -> new RateDto(rate.getCurrencyTo().getCurrencyCode(),rate.getBuy().toString(),rate.getSell().toString()))
                .collect(Collectors.toList());
    }

}