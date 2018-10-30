package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.ProductType;
import com.mis_ofertas_api.app.model.Rol;
import com.mis_ofertas_api.app.repository.ProductTypeDAO;
import com.mis_ofertas_api.app.repository.RolDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolRestController {

    private RolDAO rolDAO;

    @Autowired
    public void setRolDAO(RolDAO rolDAO) {
        this.rolDAO = rolDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Rol rol(@PathVariable Long id) {
        return rolDAO.rol(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Rol> rols() {
        return rolDAO.rols();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Rol create(@RequestBody Rol rol) {
        try {
            rol.setId(null);
            rolDAO.insert(rol);
            return rol;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Rol edit(@RequestBody Rol rol) {
        try {
            rolDAO.update(rol);
            return rol;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Rol rol) {
        try {
            rolDAO.update(rol);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
