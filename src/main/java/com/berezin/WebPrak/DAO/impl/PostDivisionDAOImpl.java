package com.berezin.WebPrak.DAO.impl;


import com.berezin.WebPrak.DAO.PostDivisionDAO;
import com.berezin.WebPrak.models.PostDivision;
import org.springframework.stereotype.Repository;



@Repository
public class PostDivisionDAOImpl extends CommonDAOImpl<PostDivision, Integer> implements PostDivisionDAO  {
    public PostDivisionDAOImpl() {
        super(PostDivision.class);
    }
    private String likeTemplate(String s) {
        return "%" + s + "%";
    }
}