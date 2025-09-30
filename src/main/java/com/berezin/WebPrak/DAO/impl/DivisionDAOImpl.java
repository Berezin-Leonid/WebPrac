package com.berezin.WebPrak.DAO.impl;


import com.berezin.WebPrak.DAO.DivisionDAO;
import com.berezin.WebPrak.models.Division;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DivisionDAOImpl extends CommonDAOImpl<Division, Integer> implements DivisionDAO  {
    public DivisionDAOImpl() {
        super(Division.class);
    }
    private String likeTemplate(String s) {
        return "%" + s + "%";
    }
}