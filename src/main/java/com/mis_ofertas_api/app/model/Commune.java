package com.mis_ofertas_api.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Commune implements Serializable, Bean, LazyCollectorBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(length = 256, nullable = false)
    private String name;


    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;


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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public void fetchCollections() {

    }
}
