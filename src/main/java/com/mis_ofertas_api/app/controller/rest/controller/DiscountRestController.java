package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Country;
import com.mis_ofertas_api.app.model.Discount;
import com.mis_ofertas_api.app.repository.CountryDAO;
import com.mis_ofertas_api.app.repository.DiscountDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountRestController {

    private DiscountDAO discountDAO;

    @Autowired
    public void setDiscountDAO(DiscountDAO discountDAO) {
        this.discountDAO = discountDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Discount discount(@PathVariable Long id) {
        return discountDAO.discount(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Discount> discounts() {
        return discountDAO.discounts();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Discount create(@RequestBody Discount discount) {
        try {
            discountDAO.insert(discount);
            return discount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Discount edit(@RequestBody Discount discount) {
        try {
            discountDAO.update(discount);
            return discount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Discount discount) {
        try {
            discountDAO.update(discount);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
