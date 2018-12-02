package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.repository.AreaDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaRestController {

    private AreaDAO areaDAO;

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO = areaDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Area area(@PathVariable Long id) {
        return areaDAO.area(id);
    }

    @RequestMapping(path = "/list/{textSearch}", method = RequestMethod.GET)
    public List<Area> areas(@PathVariable String textSearch) {
        return areaDAO.areas(textSearch);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Area> areas() {
        return areaDAO.areas();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Area create(@RequestBody Area area) {
        try {
            area.setId(null);
            areaDAO.insert(area);
            return area;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public Area edit(@RequestBody Area area) {
        try {
            areaDAO.update(area);
            return area;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Area area) {
        try {
            areaDAO.update(area);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
