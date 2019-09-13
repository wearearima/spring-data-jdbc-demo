package eu.arima.springdatajdbcdemo.country;

import eu.arima.springdatajdbcdemo.city.City;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer population;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country")
    private Set<City> cities = new HashSet<>();


    public Country() {
    }

    public Country(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public Country(Integer id, String name, Integer population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
