package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.repository.UserDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SystemUser systemUser(@PathVariable Long id) {
        return userDAO.systemUser(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<SystemUser> systemUsers() {
        return userDAO.systemUsers();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public SystemUser create(@RequestBody SystemUser systemUser) {
        try {
            userDAO.insert(systemUser);
            return systemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public SystemUser edit(@RequestBody SystemUser systemUser) {
        try {
            userDAO.update(systemUser);
            return systemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody SystemUser systemUser) {
        try {
            userDAO.update(systemUser);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
