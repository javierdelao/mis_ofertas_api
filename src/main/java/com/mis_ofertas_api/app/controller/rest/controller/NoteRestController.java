package com.mis_ofertas_api.app.controller.rest.controller;

import com.mis_ofertas_api.app.model.Note;
import com.mis_ofertas_api.app.repository.NoteDAO;
import com.mis_ofertas_api.app.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteRestController {

    private NoteDAO noteDAO;

    @Autowired
    public void setNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Note note(@PathVariable Long id) {
        return noteDAO.note(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Note> notes() {
        return noteDAO.notes();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Note create(@RequestBody Note note) {
        try {
            noteDAO.insert(note);
            return note;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/edit", method = RequestMethod.PUT)
    public Note edit(@RequestBody Note note) {
        try {
            noteDAO.update(note);
            return note;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public SuccessResponse delete(@RequestBody Note note) {
        try {
            noteDAO.update(note);
            return new SuccessResponse(true, "success");
        } catch (Exception e) {
            return new SuccessResponse(true, e.getMessage());
        }
    }


}
