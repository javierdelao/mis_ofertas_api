package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.City;
import com.mis_ofertas_api.app.repository.CityDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityRestController {

    private CityDAO cityDAO;

    @Autowired
    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public City city(@PathVariable Long id) {
        return cityDAO.city(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<City> cities() {
        return cityDAO.cities();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public City create(@RequestBody City city) {
        try {
            city.setId(null);
            cityDAO.insert(city);
            return city;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public City edit(@RequestBody City city) {
        try {
            cityDAO.update(city);
            return city;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody City city) {
        try {
            cityDAO.update(city);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
