package com.berezin.WebPrak.DAO.impl;


import com.berezin.WebPrak.DAO.PostDAO;
import com.berezin.WebPrak.models.Post;
import org.springframework.stereotype.Repository;



@Repository
public class PostDAOImpl extends CommonDAOImpl<Post, Integer> implements PostDAO  {
    public PostDAOImpl() {
        super(Post.class);
    }
    private String likeTemplate(String s) {
        return "%" + s + "%";
    }
}