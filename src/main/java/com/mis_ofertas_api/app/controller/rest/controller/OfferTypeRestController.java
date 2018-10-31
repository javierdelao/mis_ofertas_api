package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.OfferType;
import com.mis_ofertas_api.app.repository.OfferTypeDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offertype")
public class OfferTypeRestController {

    private OfferTypeDAO offerTypeDAO;

    @Autowired
    public void setOfferTypeDAO(OfferTypeDAO offerTypeDAO) {
        this.offerTypeDAO = offerTypeDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OfferType offer(@PathVariable Long id) {
        return offerTypeDAO.offerType(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<OfferType> offers() {
        return offerTypeDAO.offerTypes();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public OfferType create(@RequestBody OfferType offerType) {
        try {
            offerTypeDAO.insert(offerType);
            return offerType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public OfferType edit(@RequestBody OfferType offerType) {
        try {
            offerTypeDAO.update(offerType);
            return offerType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody OfferType offerType) {
        try {
            offerTypeDAO.update(offerType);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
