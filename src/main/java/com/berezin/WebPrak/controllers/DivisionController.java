package com.berezin.WebPrak.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.berezin.WebPrak.models.Division;
import com.berezin.WebPrak.DAO.DivisionDAO;
import com.berezin.WebPrak.DAO.impl.DivisionDAOImpl;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DivisionController {

    @Autowired
    private final DivisionDAO divisionDAO = new DivisionDAOImpl();

    @GetMapping("/divisions")
    public String divisiontListPage(Model model) {
        List<Division> division = (List<Division>) divisionDAO.getAll();
        model.addAttribute("divisions", division);
        model.addAttribute("divisionService", divisionDAO);
        return "divisions";
    }

}