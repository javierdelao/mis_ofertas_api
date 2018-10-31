package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Offer;
import com.mis_ofertas_api.app.repository.OfferDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferRestController {

    private OfferDAO offerDAO;

    @Autowired
    public void setOfferDAO(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Offer offer(@PathVariable Long id) {
        return offerDAO.offer(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Offer> offers() {
        return offerDAO.offers();
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
