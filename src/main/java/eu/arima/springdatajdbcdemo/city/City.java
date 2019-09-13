package eu.arima.springdatajdbcdemo.city;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class City {
    @Id
    @Column("ci_id")
    private Integer id;
    @Column("ci_name")
    private String name;
    @Column("ci_population")
    private Integer population;

    public City() {
    }

    public City(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public City(Integer id, String name, Integer population) {
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

    @Override
    public String toString() {
        return String.format("{%d,%s,%d}", this.id, this.name, this.population);
    }
}
