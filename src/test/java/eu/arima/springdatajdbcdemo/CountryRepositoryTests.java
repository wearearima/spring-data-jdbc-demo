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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryRepositoryTests {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CountryPageableRepository countryPageableRepository;
    @Autowired
    EntityManager entityManager;
    Logger LOGGER = LoggerFactory.getLogger(CountryRepositoryTests.class);

    @Test
    public void findAllReturnsEmpty() {
        final Iterable<Country> all = countryRepository.findAll();
        assertEquals(0, StreamSupport.stream(all.spliterator(), false).count());
    }

    @Test
    public void reloadModel1() {
        Country country = createCountry();
        countryRepository.save(country);
        final Optional<Country> loaded = countryRepository.findById(country.getId());
        assertTrue(loaded.get() == country);
    }

    @Test
    public void reloadModel2() {
        Country country = createCountry();
        countryRepository.save(country);
        entityManager.clear();
        final Optional<Country> loaded = countryRepository.findById(country.getId());
        assertFalse(loaded.get() == country);
    }

    @Test
    public void lazyLoad() {
        Country country = createCountry();
        countryRepository.save(country);
        entityManager.clear();
        final Optional<Country> loaded = countryRepository.findById(country.getId());
        LOGGER.info("Se ha hecho el find, pero solo se ha ejecutado una SELECT");
        assertEquals(3, loaded.get().getCities().size());
        LOGGER.info("Al utilizar el SET se han obtenido las relaciones");
    }

    @Test
    public void update() {
        Country country = createCountry();
        countryRepository.save(country);
        country.setPopulation(10);
        countryRepository.save(country);
        LOGGER.info("Se ha hecho save, pero el guardado no se ha aplicado aún");
        entityManager.clear();
        final Optional<Country> loaded = countryRepository.findById(country.getId());
        assertNotEquals((Integer)10, loaded.get().getPopulation());
    }

    @Test
    public void update2() {
        Country country = createCountry();
        countryRepository.save(country);
        country.setPopulation(10);
        entityManager.flush();
        LOGGER.info("Sin hacer save, se ha hecho un flush, ahora se ha guardado");
        entityManager.clear();
        final Optional<Country> loaded = countryRepository.findById(country.getId());
        assertEquals((Integer)10, loaded.get().getPopulation());
    }
    @Test
    public void findPage() {
        Country country = createCountry();
        countryRepository.save(country);
        final PageRequest firstPageRequest = PageRequest.of(0, 10);
        final Page<Country> firstPage = countryPageableRepository.findAll(firstPageRequest);
        assertEquals(1, firstPage.getTotalElements());
    }

    private Country createCountry() {
        Country country = new Country("Spain", 46720000);
        City donosti = new City("Donosti", 186665, country);
        City bilbo = new City("Bilbao", 345821, country);
        City madrid = new City("Madrid", 3174000, country);
        country.setCities(Sets.newSet(madrid, donosti, bilbo));
        return country;
    }
}
