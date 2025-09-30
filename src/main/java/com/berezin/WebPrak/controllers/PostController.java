package com.berezin.WebPrak.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.berezin.WebPrak.models.Post;
import com.berezin.WebPrak.DAO.PostDAO;
import com.berezin.WebPrak.DAO.impl.PostDAOImpl;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private final PostDAO postDAO = new PostDAOImpl();

    @GetMapping("/posts")
    public String postListPage(Model model) {
        List<Post> posts = (List<Post>) postDAO.getAll();
        model.addAttribute("posts", posts);
        model.addAttribute("postService", postDAO);
        return "posts";
    }

}