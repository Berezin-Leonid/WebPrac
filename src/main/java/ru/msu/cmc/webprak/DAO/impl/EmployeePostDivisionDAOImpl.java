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

    @Override
    public List<EmployeePostDivision> getByFilter(Filter filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<EmployeePostDivision> criteriaQuery = builder.createQuery(EmployeePostDivision.class);
            Root<EmployeePostDivision> root = criteriaQuery.from(EmployeePostDivision.class);
            Join<EmployeePostDivision, Employee> employeeJoin = root.join("employee");
            Join<EmployeePostDivision, PostDivision> postDivisionJoin = root.join("postDivision");

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getFullName() != null)
                predicates.add(builder.like(employeeJoin.get("fullName"), likeExpr(filter.getFullName())));

            if (filter.getPosition() != null)
                predicates.add(builder.like(postDivisionJoin.get("position"), likeExpr(filter.getPosition())));

            if (filter.getDivision() != null)
                predicates.add(builder.like(postDivisionJoin.get("division"), likeExpr(filter.getDivision())));

            if (!predicates.isEmpty())
                criteriaQuery.where(predicates.toArray(new Predicate[0]));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    private String likeExpr(String value) {
        return "%" + value + "%";
    }

}