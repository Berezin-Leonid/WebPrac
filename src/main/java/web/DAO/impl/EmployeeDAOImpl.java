package web.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.DAO.EmployeeDAO;
import web.models.Employee;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImpl extends CommonDAOImpl<Employee, Long> implements EmployeeDAO {

    public EmployeeDAOImpl(){
        super(Employee.class);
    }

    @Override
    public List<Employee> getAllEmployeeByName(String employeeName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee WHERE name LIKE :gotName", Employee.class)
                    .setParameter("gotName", likeExpr(employeeName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Employee getSingleEmployeeByName(String employeeName) {
        List<Employee> candidates = this.getAllEmployeeByName(employeeName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }


    @Override
    public List<Employee> getByFilter(Filter filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null)
                predicates.add(builder.like(root.get("name"), likeExpr(filter.getName())));

            if (predicates.size() != 0)
                criteriaQuery.where(predicates.toArray(new Predicate[0]));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}