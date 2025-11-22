package com.trueguiders.model;

import jakarta.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;

    public City() {}

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    // GETTER – SETTER
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }

    public void setName(String name) { this.name = name; }
    public void setCountry(String country) { this.country = country; }
}
