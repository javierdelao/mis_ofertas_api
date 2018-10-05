package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Image;
import com.mis_ofertas_api.app.repository.ImageDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageRestController {

    private ImageDAO imageDAO;

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Image image(@PathVariable Long id) {
        return imageDAO.image(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Image> images() {
        return imageDAO.images();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Image create(@RequestBody Image image) {
        try {
            imageDAO.insert(image);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Image edit(@RequestBody Image image) {
        try {
            imageDAO.update(image);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Image image) {
        try {
            imageDAO.update(image);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
