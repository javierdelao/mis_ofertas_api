package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.repository.UserDAO;
import com.mis_ofertas_api.app.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ldapController {

    private UserDAO userDAO;


    @Autowired
    public void setUsuarioDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public SystemUser test() {
        SystemUser user = new SystemUser();
        user.setFirstName("test user");
        return user;
    }

    @RequestMapping(path = "/login/{rut}/{password}", method = RequestMethod.GET)
    public LoginResponse login(@PathVariable String rut,
                               @PathVariable String password){
        LoginResponse loginResponse=new LoginResponse();
        SystemUser user=new SystemUser();
        user.setRut(rut);
        user.setPassword(password);
        user=userDAO.user(user);
        loginResponse.setUser(user);
        if(user!=null){
            if(user.getPassword().equals(password)){
                loginResponse.setStatus("success");
            }else{
                loginResponse.setStatus("invalid");
            }

        }else{
            loginResponse.setStatus("invalid");
        }
        return loginResponse;
    }


/*    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponse registrarUsuario(@RequestBody Usuario usuarioParam){
        LoginResponse loginResponse=new LoginResponse();
       try {
           userDAO.insert(usuarioParam);
           loginResponse.setStatus("success");
       }catch (Exception e){
           usuarioParam=null;
           loginResponse.setStatus(e.getMessage());
       }
        loginResponse.setUsuario(usuarioParam);
        return loginResponse;
    }

*/


  /*  @RequestMapping(path = "/pago", method = RequestMethod.POST)
    public PagoResponse registrarPago(@RequestBody Infraccion infraccion){

        PagoResponse pagoResponse=new PagoResponse();
        try {
            infraccion.setEstadoInfraccion(estadoInfraccionDAO.estadoInfraccion("Pagada"));
            infraccionDAO.update(infraccion);
            pagoResponse.setStatus("success");
        }catch (Exception e){
            pagoResponse.setStatus(e.getMessage());
        }
        pagoResponse.setInfraccion(infraccion);
        return pagoResponse;
    }
*/

 /*   @RequestMapping(path = "/infraccion", method = RequestMethod.POST)
    public InfraccionResponse registrarInfraccion(@RequestBody Infraccion infraccion){
        InfraccionResponse response=new InfraccionResponse();
        try {
            infraccionDAO.insert(infraccion);
            response.setStatus("success");
        }catch (Exception e){
            response.setStatus(e.getMessage());
        }
        response.setInfraccion(infraccion);
        return response;
    }
    */
}
