package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.DivisionDAO;
import ru.msu.cmc.webprak.models.Division;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DivisionDAOImpl extends CommonDAOImpl<Division, Long> implements DivisionDAO {

    public DivisionDAOImpl(){
        super(Division.class);
    }


    @Override
    public Division getSingleDivisionByName(String divisionName) {
        List<Division> candidates = this.getAllDivisionByName(divisionName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }


    @Override
    public List<Division> getByFilter(Filter filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Division> criteriaQuery = builder.createQuery(Division.class);
            Root<Division> root = criteriaQuery.from(Division.class);

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null)
                predicates.add(builder.like(root.get("name"), likeExpr(filter.getName())));
            if (filter.parentId() != null)
                predicates.add(builder.like(root.get("parent_id"), likeExpr(filter.getParentId())));
            if (predicates.size() != 0)
                criteriaQuery.where(predicates.toArray(new Predicate[0]));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}