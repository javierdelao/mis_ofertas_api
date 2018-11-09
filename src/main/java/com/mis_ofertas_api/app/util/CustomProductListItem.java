package com.mis_ofertas_api.app.util;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.Product;

import java.util.List;

public class CustomProductListItem {

    private Area area;

    private List<Product> products;

    public CustomProductListItem() {
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
