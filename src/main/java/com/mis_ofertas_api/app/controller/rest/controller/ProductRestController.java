package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.OfferType;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.repository.ImageDAO;
import com.mis_ofertas_api.app.repository.OfferTypeDAO;
import com.mis_ofertas_api.app.repository.ProductDAO;
import com.mis_ofertas_api.app.repository.UserDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private ProductDAO productDAO;

    private ImageDAO imageDAO;

    private UserDAO userDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product product(@PathVariable Long id) {
        return productDAO.product(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Product> products() {
        return productDAO.products(null,false,false);
    }

    @RequestMapping(path = "/list/{userId}/{owner}/{active}", method = RequestMethod.GET)
    public List<Product> products(
            @PathVariable Long userId,
            @PathVariable Boolean owner,
            @PathVariable Boolean active) {

        return productDAO.products(
                userDAO.systemUser(userId),
                owner,
                active
        );
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        try {
            imageDAO.insert(product.getImage());
            productDAO.insert(product);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
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
