package eu.arima.springdatajdbcdemo.city;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {
    
}
