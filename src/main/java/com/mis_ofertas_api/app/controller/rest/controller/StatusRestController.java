package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Status;
import com.mis_ofertas_api.app.repository.StatusDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusRestController {

    private StatusDAO statusDAO;

    @Autowired
    public void setStatusDAO(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Status status(@PathVariable Long id) {
        return statusDAO.status(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Status> statuses() {
        return statusDAO.statuses();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Status create(@RequestBody Status status) {
        try {
            statusDAO.insert(status);
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Status edit(@RequestBody Status status) {
        try {
            statusDAO.update(status);
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Status status) {
        try {
            statusDAO.update(status);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
