package eu.arima.springdatajdbcdemo.city;

import eu.arima.springdatajdbcdemo.country.Country;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer population;
    @ManyToOne()
    @JoinColumn(name = "country")
    private Country country;

    public City() {
    }

    public City(String name, Integer population, Country country) {
        this.name = name;
        this.population = population;
        this.country = country;
    }

    public City(Integer id, String name, Integer population, Country country) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.country = country;
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
}
