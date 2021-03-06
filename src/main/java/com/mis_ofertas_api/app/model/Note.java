package com.mis_ofertas_api.app.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Note implements Serializable, Bean, LazyCollectorBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Date commentDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SystemUser systemUser;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Transient
    private Valoration valoration;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "note_document",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Document> documents;


    public Note() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
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

    public Valoration getValoration() {
        return valoration;
    }

    public void setValoration(Valoration valoration) {
        this.valoration = valoration;
    }

    @Override
    public void fetchCollections() {

    }


}


