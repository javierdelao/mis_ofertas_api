package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Commune;
import com.mis_ofertas_api.app.repository.CommuneDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commune")
public class CommuneRestController {

    private CommuneDAO communeDAO;

    @Autowired
    public void setCommuneDAO(CommuneDAO communeDAO) {
        this.communeDAO = communeDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Commune commune(@PathVariable Long id) {
        return communeDAO.commune(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Commune> communes() {
        return communeDAO.communes();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Commune create(@RequestBody Commune commune) {
        try {
            commune.setId(null);
            communeDAO.insert(commune);
            return commune;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Commune edit(@RequestBody Commune commune) {
        try {
            communeDAO.update(commune);
            return commune;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Commune commune) {
        try {
            communeDAO.update(commune);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
