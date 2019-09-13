package eu.arima.springdatajdbcdemo;

import eu.arima.springdatajdbcdemo.country.Country;
import eu.arima.springdatajdbcdemo.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public Iterable<Country> countries() {
        final Iterable<Country> countries = countryService.findAll();
        return countries;
    }
}
