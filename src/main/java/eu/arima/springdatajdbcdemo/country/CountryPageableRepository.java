package eu.arima.springdatajdbcdemo.country;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryPageableRepository extends PagingAndSortingRepository<Country, Integer> {
}
