package com.berezin.WebPrak.DAO.impl;


import com.berezin.WebPrak.DAO.impl.CommonDAOImpl;
import com.berezin.WebPrak.models.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.berezin.WebPrak.DAO.EmployeeDAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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