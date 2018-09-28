package com.mis_ofertas_api.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(indexes = {},
        uniqueConstraints = {
                @UniqueConstraint(name = "country_name_unique", columnNames = {"name"})
        })
public class Country implements Serializable, Bean, LazyCollectorBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(length = 256, nullable = false)
    private String name;


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

    @Override
    public void fetchCollections() {

    }
}
