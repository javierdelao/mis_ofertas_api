package com.mis_ofertas_api.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class City implements Serializable, Bean, LazyCollectorBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(length = 256, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="country_id", nullable=false)
    private Country country;

    public City() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public void fetchCollections() {

    }
}
