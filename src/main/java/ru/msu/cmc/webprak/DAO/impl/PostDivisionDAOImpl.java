package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.PostDivisionDAO;
import ru.msu.cmc.webprak.models.PostDivision;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDivisionDAOImpl extends CommonDAOImpl<PostDivision, Long> implements PostDivisionDAO {

    public PostDivisionDAOImpl(){
        super(PostDivision.class);
    }
}