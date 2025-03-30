package web.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.DAO.EmployeePostDivisionDAO;
import web.models.EmployeePostDivision;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeePostDivisionDAOImpl extends CommonDAOImpl<EmployeePostDivision, Long> implements EmployeePostDivisionDAO {

    public EmployeePostDivisionDAOImpl(){
        super(EmployeePostDivision.class);
    }


    @Override
    public List<EmployeePostDivision> getByPostDivisionId(Long postDivisionID) {
        try (Session session = sessionFactory.openSession()) {
            Query<EmployeePostDivision> query = session.createQuery(
                            "FROM EmployeePostDivision WHERE postDivision.id = :postDivisionID", EmployeePostDivision.class)
                    .setParameter("postDivisionID", postDivisionID);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<EmployeePostDivision> getByEmployeeId(Long employeeID) {
        try (Session session = sessionFactory.openSession()) {
            Query<EmployeePostDivision> query = session.createQuery(
                            "FROM EmployeePostDivision WHERE employee.id = :employeeID", EmployeePostDivision.class)
                    .setParameter("employeeID", employeeID);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }


    
    
    /*
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

     */

    private String likeExpr(String value) {
        return "%" + value + "%";
    }

}