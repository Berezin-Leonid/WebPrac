package com.berezin.WebPrak.DAO;

import com.berezin.WebPrak.DAO.impl.CommonDAOImpl;
import com.berezin.WebPrak.models.Employee;
import java.util.List;

public interface EmployeeDAO extends CommonDAO<Employee, Integer> {
    List<Employee> getAllEmployeeByName(String name);
}