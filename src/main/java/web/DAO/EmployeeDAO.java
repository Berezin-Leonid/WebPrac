package web.DAO;

import lombok.Builder;
import lombok.Getter;
import web.models.Employee;

import java.util.List;

public interface EmployeeDAO extends CommonDAO<Employee, Long> {

    List<Employee> getAllEmployeeByName(String employeeName);
    Employee getSingleEmployeeByName(String employeeName);
    List<Employee> getByFilter(Filter filter);


    @Builder
    @Getter
    class Filter {
        private String name;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}