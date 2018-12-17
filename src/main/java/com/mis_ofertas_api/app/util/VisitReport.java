package com.mis_ofertas_api.app.util;


import com.mis_ofertas_api.app.model.Product;

public class VisitReport {

    private Integer visitQta;

    private Product product;

    public VisitReport( ) {

    }

    public Integer getVisitQta() {
        return visitQta;
    }

    public void setVisitQta(Integer visitQta) {
        this.visitQta = visitQta;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
