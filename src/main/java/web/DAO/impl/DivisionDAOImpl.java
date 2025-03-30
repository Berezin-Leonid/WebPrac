package web.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.DAO.DivisionDAO;
import web.models.Division;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DivisionDAOImpl extends CommonDAOImpl<Division, Long> implements DivisionDAO {

    public DivisionDAOImpl(){
        super(Division.class);
    }



    @Override
    public List<Division> getAllDivisionByName(String employeeName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Division> query = session.createQuery("FROM Division WHERE name LIKE :gotName", Division.class)
                    .setParameter("gotName", likeExpr(employeeName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Division getSingleDivisionByName(String divisionName) {
        List<Division> candidates = this.getAllDivisionByName(divisionName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }

    @Override
    public List<Division> getAllDivisionByParentId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Division> query = session.createQuery("FROM Division WHERE parent.id = :parentId", Division.class)
                    .setParameter("parentId", id);
            return query.getResultList().isEmpty() ? null : query.getResultList();
        }
    }

    /*
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

     */


    private String likeExpr(String param) {
        return "%" + param + "%";
    }



}