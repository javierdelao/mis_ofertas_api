package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.model.Visit;
import com.mis_ofertas_api.app.repository.ProductDAO;
import com.mis_ofertas_api.app.repository.UserDAO;
import com.mis_ofertas_api.app.repository.VisitDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import com.mis_ofertas_api.app.util.VisitReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitRestController {

    private VisitDAO visitDAO;

    private UserDAO userDAO;

    private ProductDAO productDAO;

    @Autowired
    public void setVisitDAO(VisitDAO visitDAO) {
        this.visitDAO = visitDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Visit visit(@PathVariable Long id) {
        return visitDAO.visit(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Visit> visits() {
        return visitDAO.visits();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Visit create(@RequestBody Visit visit) {
        try {
            visitDAO.insert(visit);
            return visit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Visit edit(@RequestBody Visit visit) {
        try {
            visitDAO.update(visit);
            return visit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Visit visit) {
        try {
            visitDAO.update(visit);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }

    @RequestMapping(path = "/qta/{productId}", method = RequestMethod.GET)
    public List<Visit> qta(@PathVariable Long productId) {
        try {
           return visitDAO.qta(productId);
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(path = "/qta2/{systemUserId}", method = RequestMethod.GET)
    public List<VisitReport> qta2(@PathVariable Long systemUserId) {
        try {
            SystemUser systemUser=userDAO.systemUser(systemUserId);
            List<Product>products=productDAO.products(systemUser,true,null,null,null,null,null);

            return visitDAO.qta2(products);
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(path = "/visitperday/{productId}", method = RequestMethod.POST)
    public List<Visit> visitPerDay(@PathVariable Long productId,@RequestBody Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            Date date2=calendar.getTime();
            return visitDAO.visitPerDay(productId,date,date2);
        } catch (Exception e) {
            throw e;
        }
    }




}
