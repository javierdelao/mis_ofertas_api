package com.mis_ofertas_api.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {
                @UniqueConstraint(name = "rut_unique", columnNames = {"rut"})
        })
public class SystemUser implements Serializable, Bean, LazyCollectorBean{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(length = 12, nullable = false)
        private String rut;

        @Basic
        @Column(length = 128, nullable = false)
        private String firstName;

        @Basic
        @Column(length = 128, nullable = false)
        private String lastName;

        @Basic
        @Column(length = 128, nullable = false)
        private String email;

        @Basic
        @Column(length = 128, nullable = false)
        private String password;

        @ManyToOne
        @JoinColumn(name="rol_id", nullable=false)
        private Rol rol;

        @ManyToOne
        @JoinColumn(name="store_id", nullable=true)
        private Store store;

        @Basic
        @NotNull
        private Integer points;

        public SystemUser() {
        }

        @Override
        public Long getId() {
                return id;
                }

        @Override
        public void setId(Long id) {
                this.id = id;
                }

        public String getRut() {
                return rut;
                }

        public void setRut(String rut) {
                this.rut = rut;
                }

        public String getFirstName() {
                return firstName;
                }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
                }

        public String getLastName() {
                return lastName;
                }

        public void setLastName(String lastName) {
                this.lastName = lastName;
                }

        public Rol getRol() {
                return rol;
                }

        public void setRol(Rol rol) {
                this.rol = rol;
                }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Store getStore() {
                return store;
        }

        public void setStore(Store store) {
                this.store = store;
        }

        @Override
        public void fetchCollections() {

                }


}


