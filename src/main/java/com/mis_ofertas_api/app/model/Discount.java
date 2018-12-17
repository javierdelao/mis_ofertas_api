package com.mis_ofertas_api.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "code_unique", columnNames = {"code"})
})
public class Discount implements Serializable, Bean, LazyCollectorBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(length = 256, nullable = false)
    private String code;

    @Column(nullable = false)
    private Boolean used;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date expirationDate;


    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private Integer percentage;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private SystemUser user;

    public Discount() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    @Override
    public void fetchCollections() {

    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
