package com.berezin.WebPrak.DAO;

import com.berezin.WebPrak.models.PostDivision;
import com.berezin.WebPrak.models.EmployeePostDivision;
import java.util.List;

public interface EmployeePostDivisionDAO extends CommonDAO<EmployeePostDivision, Integer> {
    List<EmployeePostDivision> getAllPostDivision(Integer employeeId);
}