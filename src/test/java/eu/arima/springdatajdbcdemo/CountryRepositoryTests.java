package eu.arima.springdatajdbcdemo;

import eu.arima.springdatajdbcdemo.city.City;
import eu.arima.springdatajdbcdemo.country.Country;
import eu.arima.springdatajdbcdemo.country.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJdbcTest
public class CountryRepositoryTests {

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void findAllReturnsEmpty() {
        final Iterable<Country> all = countryRepository.findAll();
        assertEquals(0, StreamSupport.stream(all.spliterator(), false).count());
    }

    @Test
    public void saveACountry() {
        final Country country = createCountryModel();
        countryRepository.save(country);
        final Optional<Country> savedCountry = countryRepository.findById(country.getId());
        System.out.println(savedCountry);
    }

    @Test
    public void updateACountry() {
        Country country = createCountryModel();
        countryRepository.save(country);
        country.setPopulation(10);
        countryRepository.save(country);
        System.out.println(country);
    }

    private Country createCountryModel() {
        City donosti = new City("Donosti", 186665);
        City bilbo = new City("Bilbao", 345821);
        City madrid = new City("Madrid", 3174000);
        return new Country("Spain", 46720000, Sets.newSet(madrid, donosti, bilbo));
    }

}
