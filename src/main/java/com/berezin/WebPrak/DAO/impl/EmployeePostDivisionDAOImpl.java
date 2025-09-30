package com.berezin.WebPrak.DAO.impl;


import com.berezin.WebPrak.DAO.EmployeePostDivisionDAO;
import com.berezin.WebPrak.models.EmployeePostDivision;
import com.berezin.WebPrak.models.PostDivision;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeePostDivisionDAOImpl extends CommonDAOImpl<EmployeePostDivision, Integer> implements EmployeePostDivisionDAO  {
    public EmployeePostDivisionDAOImpl() {
        super(EmployeePostDivision.class);
    }

    @Override
    public List<EmployeePostDivision> getAllPostDivision(Integer employeeId) {
        try (Session session = sessionFactory.openSession()) {
            Query<EmployeePostDivision> query = session.createQuery(
                    "FROM EmployeePostDivision epd WHERE epd.employee.id = :employeeId",
                    EmployeePostDivision.class
            );
            query.setParameter("employeeId", employeeId);
            return query.getResultList();
        }
    }
}