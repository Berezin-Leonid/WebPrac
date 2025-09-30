package com.berezin.WebPrak.DAO.impl;


import com.berezin.WebPrak.DAO.PostDAO;
import com.berezin.WebPrak.models.Post;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl extends CommonDAOImpl<Post, Integer> implements PostDAO  {
    public PostDAOImpl() {
        super(Post.class);
    }
    private String likeTemplate(String s) {
        return "%" + s + "%";
    }
}