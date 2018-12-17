package com.mis_ofertas_api.app.util;


import com.mis_ofertas_api.app.model.Product;

public class ProductValorationAverage {

    private Double averageValorationProduct;

    private Product product;

    public ProductValorationAverage() {

    }

    public Double getAverageValorationProduct() {
        return averageValorationProduct;
    }

    public void setAverageValorationProduct(Double averageValorationProduct) {
        this.averageValorationProduct = averageValorationProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
