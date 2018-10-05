package com.mis_ofertas_api.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Valoration implements Serializable, Bean, LazyCollectorBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    private Integer valoration_star;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SystemUser systemUser;

    @OneToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private Note note;

    public Valoration() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValoration_star() {
        return valoration_star;
    }

    public void setValoration_star(Integer valoration_star) {
        this.valoration_star = valoration_star;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public void fetchCollections() {

    }


}


