package eu.arima.springdatajdbcdemo;

import eu.arima.springdatajdbcdemo.city.City;
import eu.arima.springdatajdbcdemo.country.Country;
import eu.arima.springdatajdbcdemo.country.CountryPageableRepository;
import eu.arima.springdatajdbcdemo.country.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJdbcTest
public class CountryRepositoryTests {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CountryPageableRepository countryPageableRepository;

    Logger LOGGER = LoggerFactory.getLogger(CountryRepositoryTests.class);

    @Test
    public void findAllReturnsEmpty() {
        final Iterable<Country> all = countryRepository.findAll();
        assertEquals(0, StreamSupport.stream(all.spliterator(), false).count());
    }

    @Test
    public void reloadModel() {
        final Country country = createCountryModel();
        countryRepository.save(country);
        final Optional<Country> loaded = countryRepository.findById(country.getId());
        assertFalse(country==loaded.get());
    }

    @Test
    public void notLazyLoad() {
        final Country country = createCountryModel();
        countryRepository.save(country);
        final Optional<Country> loaded = countryRepository.findById(country.getId());
        LOGGER.info("En los logs se ve que ya se ha ejecutado la consulta de la relación");
        assertEquals(3, loaded.get().getCities().size());
    }

    @Test
    public void update() {
        Country country = createCountryBasicModel();
        countryRepository.save(country);
        country.setPopulation(10);
        countryRepository.save(country);
        LOGGER.info("La query de actualizar se ha ejecutado");
    }
    @Test
    public void updateWithRelations() {
        Country country = createCountryModel();
        countryRepository.save(country);
        country.setPopulation(10);
        countryRepository.save(country);
        LOGGER.info("Se ha ejecutado tanto la actualización de la tabla Country, como los delete e inserts de city");
    }

    @Test
    public void findPage() {
        Country country = createCountryBasicModel();
        countryRepository.save(country);
        country.setId(null);
        countryRepository.save(country);
        final PageRequest firstPageRequest = PageRequest.of(0, 10);
        try {
            final Page<Country> firstPage = countryPageableRepository.findAll(firstPageRequest);
            fail();
        } catch (Exception e) {
            LOGGER.info("Spring Data JDBC no implementa pageable");
        }
    }

    private Country createCountryModel() {
        City donosti = new City("Donosti", 186665);
        City bilbo = new City("Bilbao", 345821);
        City madrid = new City("Madrid", 3174000);
        return new Country("Spain", 46720000, Sets.newSet(madrid, donosti, bilbo));
    }

    private Country createCountryBasicModel() {
        return new Country("Spain", 46720000, new HashSet<>());
    }

}
