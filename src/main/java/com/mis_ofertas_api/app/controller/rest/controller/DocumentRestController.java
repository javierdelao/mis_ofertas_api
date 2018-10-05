package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Document;
import com.mis_ofertas_api.app.repository.DocumentDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentRestController {

    private DocumentDAO documentDAO;

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Document document(@PathVariable Long id) {
        return documentDAO.document(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Document> documents() {
        return documentDAO.documents();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Document create(@RequestBody Document document) {
        try {
            documentDAO.insert(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Document edit(@RequestBody Document document) {
        try {
            documentDAO.update(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Document document) {
        try {
            documentDAO.update(document);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
