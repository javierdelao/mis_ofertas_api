package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Country;
import com.mis_ofertas_api.app.repository.CountryDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryRestController {

    private CountryDAO countryDAO;

    @Autowired
    public void setCountryDAO(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Country country(@PathVariable Long id) {
        return countryDAO.country(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Country> countries() {
        return countryDAO.countries();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Country create(@RequestBody Country country) {
        try {
            country.setId(null);
            countryDAO.insert(country);
            return country;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Country edit(@RequestBody Country country) {
        try {
            countryDAO.update(country);
            return country;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Country country) {
        try {
            countryDAO.update(country);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
