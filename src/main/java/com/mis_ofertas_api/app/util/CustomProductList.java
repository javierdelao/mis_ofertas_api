package com.mis_ofertas_api.app.util;

import java.util.List;

public class CustomProductList {

    private List<CustomProductListItem> customProductListItems;

    public CustomProductList() {
    }

    public List<CustomProductListItem> getCustomProductListItems() {
        return customProductListItems;
    }

    public void setCustomProductListItems(List<CustomProductListItem> customProductListItems) {
        this.customProductListItems = customProductListItems;
    }
}
