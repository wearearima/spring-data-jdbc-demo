package eu.arima.springdatajdbcdemo.country;

import eu.arima.springdatajdbcdemo.city.City;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.HashSet;
import java.util.Set;

public class Country {
    @Id
    @Column("c_id")
    private Integer id;

    @Column("c_name")
    private String name;
    @Column("c_population")
    private Integer population;

    @Column("ci_country")
    private Set<City> cities = new HashSet<>();


    public Country() { }


    public Country(String name, Integer population, Set<City> cities) {
        this.name = name;
        this.population = population;
        this.cities = cities;
    }

    public Country(Integer id, String name, Integer population, Set<City> cities) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("{%d,%s,%d,%s}",this.id, this.name, this.population, this.cities.toString());
    }
}
