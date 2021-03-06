package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.repository.*;
import com.mis_ofertas_api.app.response.SuccessResponse;
import com.mis_ofertas_api.app.util.CustomProductList;
import com.mis_ofertas_api.app.util.CustomProductListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private ProductDAO productDAO;

    private ImageDAO imageDAO;

    private UserDAO userDAO;

    private OfferDAO offerDAO;

    private AreaDAO areaDAO;

    private StatusDAO statusDAO;

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

    @Autowired
    public void setOfferDAO(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO = areaDAO;
    }

    @Autowired
    public void setStatusDAO(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product product(@PathVariable Long id) {
        return productDAO.product(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Product> products() {
        List<Product> products = productDAO.products(null, false, false, null, null);
        for (Product product : products) {
            product.setOffer(offerDAO.offer(product));
        }
        return products;
    }

    @RequestMapping(path = "/list/custom/{userId}", method = RequestMethod.GET)
    public CustomProductList custom(@PathVariable Long userId) {

        CustomProductList customProductList = areaDAO.areas(userDAO.systemUser(userId).getId());
        for (CustomProductListItem customProductListItem : customProductList.getCustomProductListItems()) {
            for (Product product : customProductListItem.getProducts()) {
                product.setOffer(offerDAO.offer(product));
            }
        }
        return customProductList;
    }

    @RequestMapping(path = "/list/{areaId}", method = RequestMethod.GET)
    public List<Product> products(@PathVariable Long areaId) {
        List<Product> products = productDAO.products(null, false, true, areaId, null);

        for (Product product : products) {
            product.setOffer(offerDAO.offer(product));
        }
        return products;
    }

    @RequestMapping(path = "/list/{userId}/{owner}/{active}", method = RequestMethod.GET)
    public List<Product> products(
            @PathVariable Long userId,
            @PathVariable Boolean owner,
            @PathVariable Boolean active) {
        List<Product> products = productDAO.products(
                userDAO.systemUser(userId),
                owner,
                active,
                null,
                null
        );
        for (Product product : products) {
            product.setOffer(offerDAO.offer(product));
        }

        return products;
    }

    @RequestMapping(path = "/list/{userId}/{owner}/{activeId}/{areaId}/{productTypeId}/{textSearch}", method = RequestMethod.GET)
    public List<Product> products(
            @PathVariable Long userId,
            @PathVariable Boolean owner,
            @PathVariable Long activeId,
            @PathVariable Long areaId,
            @PathVariable Long productTypeId,
            @PathVariable String textSearch) {
        List<Product> products = productDAO.products(
                userDAO.systemUser(userId),
                owner,
                activeId,
                areaId,
                null,
                productTypeId,
                textSearch
        );
        for (Product product : products) {
            product.setOffer(offerDAO.offer(product));
        }

        return products;
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
            if(product.getImage()!=null){
                if(product.getImage().getId()==null){
                    imageDAO.insert(product.getImage());
                }
            }
            productDAO.update(product);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public Product delete(@RequestBody Product product) {
        try {
            product.setStatus(statusDAO.status("Inactivo"));
            productDAO.update(product);
            return product;
        } catch (Exception e) {
            throw e;
        }
    }


}
