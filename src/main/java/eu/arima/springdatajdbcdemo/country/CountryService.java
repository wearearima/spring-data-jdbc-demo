package eu.arima.springdatajdbcdemo.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

}
