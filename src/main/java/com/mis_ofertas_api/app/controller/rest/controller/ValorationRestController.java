package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Valoration;
import com.mis_ofertas_api.app.repository.ValorationDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoration")
public class ValorationRestController {

    private ValorationDAO valorationDAO;

    @Autowired
    public void setValorationDAO(ValorationDAO valorationDAO) {
        this.valorationDAO = valorationDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Valoration valoration(@PathVariable Long id) {
        return valorationDAO.valoration(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Valoration> valorations() {
        return valorationDAO.valorations();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Valoration create(@RequestBody Valoration valoration) {
        try {
            valorationDAO.insert(valoration);
            return valoration;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Valoration edit(@RequestBody Valoration valoration) {
        try {
            valorationDAO.update(valoration);
            return valoration;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Valoration valoration) {
        try {
            valorationDAO.update(valoration);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
