package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.Store;
import com.mis_ofertas_api.app.repository.ProductDAO;
import com.mis_ofertas_api.app.repository.StoreDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreRestController {

    private StoreDAO storeDAO;

    private ProductDAO productDAO;

    @Autowired
    public void setStoreDAO(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Store store(@PathVariable Long id) {
        return storeDAO.store(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Store> stores() {
        return storeDAO.stores();
    }

    @RequestMapping(path = "/products/{storeId}", method = RequestMethod.GET)
    public List<Product> products(@PathVariable Long storeId) {
        return productDAO.products(null,false,true,null,storeId);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Store create(@RequestBody Store store) {
        try {
            storeDAO.insert(store);
            return store;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public Store edit(@RequestBody Store store) {
        try {
            storeDAO.update(store);
            return store;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Store store) {
        try {
            storeDAO.update(store);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
