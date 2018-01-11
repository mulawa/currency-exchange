package pl.marlena.currencyexchange.rates.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marlena.currencyexchange.rates.Rate;
import pl.marlena.currencyexchange.rates.provider.RateProvider;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rates")
public class RateController {

    private RateProvider rateProvider;

    @GetMapping
    public List<Rate> get() {
        return rateProvider.getRates();
    }
}
