package com.berezin.WebPrak.DAO.impl;


import com.berezin.WebPrak.DAO.EmployeeDAO;
import com.berezin.WebPrak.models.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl extends CommonDAOImpl<Employee, Integer> implements EmployeeDAO  {
    public EmployeeDAOImpl() {
        super(Employee.class);
    }

    @Override
    public List<Employee> getAllEmployeeByName(String name) {
        if (name == null || name.isBlank()) {
            return (List<Employee>) getAll();
        }

        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee WHERE name LIKE: queryName", Employee.class)
                    .setParameter("queryName", likeTemplate(name));
            return query.getResultList().isEmpty() ? null : query.getResultList();
        }
    }

    private String likeTemplate(String s) {
        return "%" + s + "%";
    }

}