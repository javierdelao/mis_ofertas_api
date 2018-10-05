package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Rol;
import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.repository.RolDAO;
import com.mis_ofertas_api.app.repository.UserDAO;
import com.mis_ofertas_api.app.response.LoginResponse;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserDAO userDAO;

    private RolDAO rolDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRolDAO(RolDAO rolDAO) {
        this.rolDAO = rolDAO;
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
            Rol rol=rolDAO.rol("CLIENT");
            systemUser.setRol(rol);
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

    @RequestMapping(path = "/login/{rut}/{password}", method = RequestMethod.GET)
    public LoginResponse login(@PathVariable String rut,
                               @PathVariable String password) {
        LoginResponse loginResponse = new LoginResponse();
        SystemUser user = new SystemUser();
        user.setRut(rut);
        user.setPassword(password);
        user = userDAO.user(user);
        loginResponse.setUser(user);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                loginResponse.setStatus("success");
            } else {
                loginResponse.setStatus("invalid");
            }

        } else {
            loginResponse.setStatus("invalid");
        }
        return loginResponse;
    }


}
