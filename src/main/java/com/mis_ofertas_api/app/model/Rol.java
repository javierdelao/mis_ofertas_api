package com.mis_ofertas_api.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {
                @UniqueConstraint(name = "rol_name_unique", columnNames = {"name"})
        })
public class Rol implements Serializable, Bean, LazyCollectorBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(length = 256, nullable = false)
    private String name;

    @Column(length = 256, nullable = false)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void fetchCollections() {

    }
}
