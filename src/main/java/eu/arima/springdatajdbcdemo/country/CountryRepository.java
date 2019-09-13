package eu.arima.springdatajdbcdemo.country;

import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
    
}
