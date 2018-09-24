package com.mis_ofertas_api.app.service;

import com.mis_ofertas_api.app.model.Rol;
import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.repository.RolDAO;
import com.mis_ofertas_api.app.util.ConfigProperties;
import com.mis_ofertas_api.app.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitialDataService {

    private ConfigProperties configProperties;

    private UserDAO userDAO;

    private RolDAO rolDAO;

    @Autowired
    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRolDAO(RolDAO rolDAO) {
        this.rolDAO = rolDAO;
    }

    @PostConstruct
    public void init() {

        if (configProperties.getMap().get("jdbc.auto").equals("update") || configProperties.getMap().get("jdbc.auto").equals("create")) {

            Rol rolAdmin = new Rol();
            rolAdmin.setDescription("tiene acceso a todas las funcionalidades del sistema");
            rolAdmin.setName("ADMIN");

            rolDAO.insert(rolAdmin);

            Rol rolRepresentative=new Rol();
            rolRepresentative.setDescription("tiene acceso a las funcionalidades de representante de algun local comercial");
            rolRepresentative.setName("REPRESENTATIVE");

            rolDAO.insert(rolRepresentative);

            Rol rolClient=new Rol();
            rolClient.setDescription("tiene acceso a las funcionalidades de representante de algun local comercial");
            rolClient.setName("CLIENT");

            rolDAO.insert(rolClient);




            SystemUser user=new SystemUser();
            user.setFirstName("Javier");
            user.setLastName("De la o");
            user.setRol(rolAdmin);
            user.setRut("18429403-6");
            user.setEmail("j.delao@alumnos.duoc.cl");
            user.setPassword("portafolio");

            userDAO.insert(user);

        }

    }


}
