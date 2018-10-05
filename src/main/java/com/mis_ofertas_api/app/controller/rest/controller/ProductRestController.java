package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.OfferType;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.repository.OfferTypeDAO;
import com.mis_ofertas_api.app.repository.ProductDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product product(@PathVariable Long id) {
        return productDAO.product(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Product> products() {
        return productDAO.products();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        try {
            productDAO.insert(product);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Product edit(@RequestBody Product product) {
        try {
            productDAO.update(product);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Product product) {
        try {
            productDAO.update(product);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
