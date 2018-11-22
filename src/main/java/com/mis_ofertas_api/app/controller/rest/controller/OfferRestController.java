package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Offer;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.repository.OfferDAO;
import com.mis_ofertas_api.app.repository.ProductDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferRestController {

    private OfferDAO offerDAO;

    private ProductDAO productDAO;

    @Autowired
    public void setOfferDAO(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Offer offer(@PathVariable Long id) {
        return offerDAO.offer(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Offer> offers() {
        return offerDAO.offers();
    }

    @RequestMapping(path = "/history/{productId}", method = RequestMethod.GET)
    public List<Offer> offerHistory(@PathVariable Long productId) {
        Product product=productDAO.product(productId);
        return offerDAO.offerHistory(product);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Offer create(@RequestBody Offer offer) {
        try {
            offerDAO.insert(offer);
            return offer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public Offer edit(@RequestBody Offer offer) {
        try {
            offerDAO.update(offer);
            return offer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Offer offer) {
        try {
            offerDAO.update(offer);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
