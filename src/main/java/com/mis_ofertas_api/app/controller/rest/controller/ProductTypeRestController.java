package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.ProductType;
import com.mis_ofertas_api.app.repository.ProductDAO;
import com.mis_ofertas_api.app.repository.ProductTypeDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producttype")
public class ProductTypeRestController {

    private ProductTypeDAO productTypeDAO;

    @Autowired
    public void setProductTypeDAO(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProductType productType(@PathVariable Long id) {
        return productTypeDAO.productType(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<ProductType> productTypes() {
        return productTypeDAO.productTypes();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ProductType create(@RequestBody ProductType productType) {
        try {
            productType.setId(null);
            productTypeDAO.insert(productType);
            return productType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public ProductType edit(@RequestBody ProductType productType) {
        try {
            productTypeDAO.update(productType);
            return productType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody ProductType productType) {
        try {
            productTypeDAO.update(productType);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
