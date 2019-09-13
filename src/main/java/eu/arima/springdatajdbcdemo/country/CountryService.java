package eu.arima.springdatajdbcdemo.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    @org.springframework.transaction.annotation.Transactional
    public Iterable<Country> findAll() {
        final Iterable<Country> all = countryRepository.findAll();
        return all;

    }

}
